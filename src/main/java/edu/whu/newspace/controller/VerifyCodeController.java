package edu.whu.newspace.controller;

import edu.whu.newspace.entity.dto.VerifyCodeResult;
import edu.whu.newspace.util.VerifyCodeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  验证码前端控制器
 * </p>
 *
 * @author Newspace
 * @since 2023-05-23
 */
@RestController
@RequestMapping("/verify-code")
public class VerifyCodeController {
    private static final int IMG_HEIGHT = 60;
    private static final int IMG_WIDTH = 140;
    private static final float INTERFERENCE_RATE = 0.5F;
    private static final float NOISE_RATE = 0.5F;

    @GetMapping("/get-verify-code")
    public ResponseEntity<VerifyCodeResult> getOneVerifyCode() {
        String code = VerifyCodeUtils.codeFen();
        String img = VerifyCodeUtils.generateCaptchaImage(
                code, IMG_WIDTH, IMG_HEIGHT, INTERFERENCE_RATE, NOISE_RATE
        );
        VerifyCodeResult verifyCodeResult = new VerifyCodeResult(code, img);
        return ResponseEntity.ok(verifyCodeResult);
    }
}
