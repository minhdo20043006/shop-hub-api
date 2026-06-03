package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.PromotionConditionDTO;
import com.example.demo.service.PromotionConditionService;
import com.example.demo.service.PromotionProductService;

@RestController
@RequestMapping({ "api/promotion-condition" })
public class PromotionConditionController {

    @Autowired
    private PromotionConditionService  promotionConditionService;

    @PostMapping("ad/add-condition/{promotionId}")
    public ResponseEntity<?> addCondition(
            @PathVariable("promotionId") Integer promotionId,
            @RequestBody PromotionConditionDTO dto) {

        return promotionConditionService.addCondition(promotionId, dto)
                ? ResponseEntity.ok("Condition added")
                : ResponseEntity.badRequest().body("Promotion not found");
    }

    @PutMapping("ad/update-condition/{id}")
    public ResponseEntity<?> updateCondition(
            @PathVariable Integer id,
            @RequestBody PromotionConditionDTO dto) {

        return promotionConditionService.updateCondition(id, dto)
                ? ResponseEntity.ok("Updated")
                : ResponseEntity.badRequest().body("Condition not found");
    }

    @DeleteMapping("ad/delete/{id}")
    public ResponseEntity<?> deleteCondition(@PathVariable Integer id) {
        promotionConditionService.deleteCondition(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("ad/find-by-promotion/{promotionId}")
    public ResponseEntity<?> findByPromotion(@PathVariable Integer promotionId) {
        return ResponseEntity.ok(
                promotionConditionService.findByPromotion(promotionId)
        );
    }
}
