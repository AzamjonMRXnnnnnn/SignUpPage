package com.example.SignUpPage.Service.Impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.SignUpPage.Model.ApplicationUser;
import com.example.SignUpPage.Model.RoleModel;
import com.example.SignUpPage.Model.request.SmsSendRequest;
import com.example.SignUpPage.Model.response.SmsSendResponse;
import com.example.SignUpPage.Repository.ApplicationRepository;
import com.example.SignUpPage.Repository.RoleRepository;
import com.example.SignUpPage.Service.ApplicationRegistrationService;
import com.example.SignUpPage.Utils.ChuangLanSmsUtil;
import com.example.SignUpPage.Utils.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class ApplicationRegistrationServiceImpl implements ApplicationRegistrationService {

    // 用户平台API账号(非登录账号,示例:N1234567)
    @Value("${message.chuanglan.api.account}")
    private String account;
    // 用户平台API密码(非登录密码)
    @Value("${message.chaunglan.api.password}")
    private String password;

    public static final String charset = "utf-8";



    @Autowired
    private VerificationCode verificationCode;

    @Autowired
    private ApplicationRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Boolean registerUser(String username, String password) {
        System.out.println("The username: " + username);
        Optional<Boolean> userExists = repository.checkUsernameIfExists(username);
        if(userExists.isPresent()){
            return false;
        }

        Optional<RoleModel> roleModel = roleRepository.findByAuthority("USER");
        ApplicationUser user = new ApplicationUser();
        Set<RoleModel> authorities = new HashSet<>();

       // authorities.add(roleModel);
        user.setPassword(passwordEncoder.encode(password));
        user.setAuthorities(authorities);
        user.setUsername(username);
        repository.save(user);
        return true;

    }

    @Override
    public Boolean registerUserWithPhoneNumber(String phoneNumber) {
        String smsSingleRequestServerUrl = "https://smssh1.253.com/msg/v1/send/json ";
        // 短信内容
        String msg = "【253云通讯】你好,你的验证码是:" + verificationCode.generateCode();
        //手机号码
//        String phone = phoneNumber;
        //状态报告
        String report= "true";
        phoneNumber = "13285713517";

        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, msg, phoneNumber,report);

        String requestJson = JSON.toJSONString(smsSingleRequest);

        System.out.println("Sending This: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);

        System.out.println("The Response :" + response);

        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

        System.out.println("response  toString is :" + smsSingleResponse);

        return true;
    }
}
