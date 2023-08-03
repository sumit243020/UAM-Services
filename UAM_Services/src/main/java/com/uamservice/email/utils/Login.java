package com.uamservice.email.utils;

public class Login {
	
//  public String register(RegisterDto registerDto) {
//  String otp = otpUtil.generateOtp();
//  try {
//    emailUtil.sendOtpEmail(registerDto.getEmail(), otp);
//  } catch (MessagingException e) {
//    throw new RuntimeException("Unable to send otp please try again");
//  }
//  User user = new User();
//  user.setName(registerDto.getName());
//  user.setEmail(registerDto.getEmail());
//  user.setPassword(registerDto.getPassword());
// user.setOtp(otp);
//  user.setOtpGeneratedTime(LocalDateTime.now());
//  userRepository.save(user);
//  return "User registration successful";
//}

//public String verifyAccount(String email, String otp) {
//  User user = userRepository.findByEmail(email)
//      .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
//  if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
//      LocalDateTime.now()).getSeconds() < (1 * 60)) {
//    user.setActive(true);
//    userRepository.save(user);
//    return "OTP verified you can login";
//  }
//  return "Please regenerate otp and try again";
//}

//public String regenerateOtp(String email) {
//  User user = userRepository.findByEmail(email)
//      .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
//  String otp = otpUtil.generateOtp();
//  try {
//    emailUtil.sendOtpEmail(email, otp);
//  } catch (MessagingException e) {
//    throw new RuntimeException("Unable to send otp please try again");
//  }
//  userRepository.save(user);
//  return "Email sent... please verify account within 1 minute";
//}

//public String login(LoginDto loginDto) {
//  User user = userRepository.findByEmail(loginDto.getEmail())
//      .orElseThrow(
//          () -> new RuntimeException("User not found with this email: " + loginDto.getEmail()));
//  if (!loginDto.getPassword().equals(user.getPassword())) {
//    return "Password is incorrect";
//  } else if (!user.isActive()) {
//    return "your account is not verified";
//  }
//  return "Login successful";
//}

}
