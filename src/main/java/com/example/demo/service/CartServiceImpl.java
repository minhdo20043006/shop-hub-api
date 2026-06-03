package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dtos.CartDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CartDTO> findByAccountId(Integer accountId) {
        List<Cart> carts = cartRepository.findByAccountId(accountId);
        return modelMapper.map(carts, new TypeToken<List<CartDTO>>() {}.getType());
    }

    @Override
    @Transactional
    public boolean addToCart(CartDTO cartDTO) {
        try {
            if (cartDTO == null || cartDTO.getAccountId() == null || cartDTO.getProductId() == null) {
                throw new IllegalArgumentException("Invalid cart data");
            }

            Account account = accountRepository.findById(cartDTO.getAccountId())
                    .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + cartDTO.getAccountId()));

            Product product = productRepository.findById(cartDTO.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + cartDTO.getProductId()));

            if (cartDTO.getQuantityCart() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0");
            }
            if (cartDTO.getQuantityCart() > product.getStockQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getNameProduct());
            }

            Optional<Cart> existingCartOpt = cartRepository.findByAccountIdAndProductId(
                    account.getId(), product.getId());

            Cart cart;
            Date now = new Date();

            // Tính subtotal trước khi áp dụng discount
            float subtotal = product.getPrice() * cartDTO.getQuantityCart();
            float discountValue = (cartDTO.getDiscount() > 0) ? cartDTO.getDiscount() : 0f;
            float totalPrice = subtotal - discountValue;

            if (existingCartOpt.isPresent()) {
                cart = existingCartOpt.get();
                int newQuantity = cart.getQuantityCart() + cartDTO.getQuantityCart();
                if (newQuantity > product.getStockQuantity()) {
                    throw new IllegalArgumentException("Total quantity exceeds available stock");
                }
                cart.setQuantityCart(newQuantity);

                // Tính lại subtotal và áp dụng discount (ưu tiên discount mới nếu DTO gửi)
                float newSubtotal = product.getPrice() * newQuantity;
                float newDiscountValue = (cartDTO.getDiscount() > 0) ? cartDTO.getDiscount() : cart.getDiscount();
                cart.setTotalPrice(newSubtotal - newDiscountValue);

                // Cập nhật discount nếu DTO gửi giá trị mới
                if (cartDTO.getDiscount() > 0) {
                    cart.setDiscount(cartDTO.getDiscount());
                }
            } else {
                cart = modelMapper.map(cartDTO, Cart.class);
                cart.setAccount(account);
                cart.setProduct(product);
                cart.setPrice(product.getPrice());
                cart.setQuantityCart(cartDTO.getQuantityCart());
                cart.setDiscount(cartDTO.getDiscount());  // Lưu mã discount (số tiền giảm)
                cart.setTotalPrice(totalPrice);
                cart.setCreatedAt(now);
            }

            cart.setUpdatedAt(now);
            cartRepository.save(cart);
            logger.info("Added/updated cart item for account {}, product {}, discount: {}", 
                        account.getId(), product.getId(), cart.getDiscount());
            return true;

        } catch (Exception e) {
            logger.error("Error adding to cart for account {}: {}", cartDTO.getAccountId(), e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public boolean updateCartItem(Integer id, CartDTO cartDTO) {
        try {
            if (cartDTO.getQuantityCart() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0");
            }

            Cart existing = cartRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Cart item not found with id: " + id));

            Product product = existing.getProduct();

            if (cartDTO.getQuantityCart() > product.getStockQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getNameProduct());
            }

            // Cập nhật quantity
            existing.setQuantityCart(cartDTO.getQuantityCart());

           
            if ( cartDTO.getDiscount() > 0) {
                existing.setDiscount(cartDTO.getDiscount());
            }

            // Tính lại totalPrice
            float subtotal = existing.getPrice() * existing.getQuantityCart();
            float discountValue = (existing.getDiscount() > 0) ? existing.getDiscount() : 0f;
            existing.setTotalPrice(subtotal - discountValue);

            existing.setUpdatedAt(new Date());
            cartRepository.save(existing);

            logger.info("Updated cart item {}, new quantity: {}, discount: {}, totalPrice: {}", 
                        id, existing.getQuantityCart(), existing.getDiscount(), existing.getTotalPrice());
            return true;

        } catch (Exception e) {
            logger.error("Error updating cart item {}: {}", id, e.getMessage(), e);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean removeCartItem(Integer id) {
        try {
            if (!cartRepository.existsById(id)) {
                return false;
            }
            cartRepository.deleteById(id);
            logger.info("Removed cart item {}", id);
            return true;
        } catch (Exception e) {
            logger.error("Error removing cart item {}: {}", id, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public float calculateTotal(Integer accountId) {
        List<Cart> carts = cartRepository.findByAccountId(accountId);
        float total = 0f;
        for (Cart cart : carts) {
            total += cart.getTotalPrice();
        }
        logger.debug("Calculated cart total for account {}: {}", accountId, total);
        return total;
    }
}