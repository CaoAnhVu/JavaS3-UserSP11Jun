//slider
document.addEventListener("DOMContentLoaded", function () {
  var mySwiper = new Swiper(".swiper-container", {
    loop: true, // Cho phép lặp lại các slide
    autoplay: {
      delay: 5000, // Thời gian hiển thị mỗi slide là 3 giây
    },
    speed: 1000, // Tốc độ chuyển động của slider (ms)
    effect: "fade", // Hiệu ứng chuyển động (vd: fade, slide, cube, flip, ...)
    navigation: {
      nextEl: ".swiper-button-next", // Selector cho nút điều hướng tiếp theo
      prevEl: ".swiper-button-prev", // Selector cho nút điều hướng trước
    },
    pagination: {
      el: ".swiper-pagination", // Selector cho điểm chuyển đổi trực quan
      clickable: true, // Cho phép điểm chuyển đổi trực quan có thể được nhấp
    },
  });
});

//show hide password
const passwordToggles = document.querySelectorAll(".password-toggle");

passwordToggles.forEach((toggle) => {
  toggle.addEventListener("click", function () {
    const targetIds = this.getAttribute("data-target").split(" ");
    targetIds.forEach((targetId) => {
      const passwordInput = document.getElementById(targetId);
      const type = passwordInput.type === "password" ? "text" : "password";
      passwordInput.setAttribute("type", type);
    });

    // Toggle the icon between eye and eye-slash
    this.classList.toggle("fa-eye");
    this.classList.toggle("fa-eye-slash");
  });
});
//login with Fb
function checkLoginState() {
  FB.getLoginStatus(function (response) {
    statusChangeCallback(response);
  });
}

function statusChangeCallback(response) {
  if (response.status === "connected") {
    FB.api("/me?fields=name,email", function (response) {
      console.log("Successful login for: " + response.name);
      console.log("Email: " + response.email);

      // Thực hiện các bước tiếp theo như gửi thông tin đến server của bạn.
    });
  } else {
    console.log("User cancelled login or did not fully authorize.");
  }
}

function fbLogin() {
  FB.login(checkLoginState, { scope: "public_profile,email" });
}
//login with google
function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  console.log("ID: " + profile.getId()); // Không chia sẻ thông tin này.
  console.log("Name: " + profile.getName());
  console.log("Image URL: " + profile.getImageUrl());
  console.log("Email: " + profile.getEmail()); // Đây là thông tin đã xác minh.

  // Thực hiện các bước tiếp theo như gửi thông tin đến server.
}

function googleSignIn() {
  var auth2 = gapi.auth2.getAuthInstance();
  auth2.signIn().then(onSignIn);
}

function initGoogleAPI() {
  gapi.load("auth2", function () {
    gapi.auth2.init();
  });
}
// Kiểm tra mật khẩu mạnh yếu
function checkPasswordStrength() {
  var password = document.getElementById("register-password").value;
  var strengthText = document.getElementById("password-strength");

  // Regular expressions to check password strength
  var strongRegex = new RegExp(
    "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})"
  );
  var mediumRegex = new RegExp("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.{8,})");

  // Check password against the regex and display strength
  if (strongRegex.test(password)) {
    strengthText.innerHTML = "Strong Password";
    strengthText.style.color = "green";
  } else if (mediumRegex.test(password)) {
    strengthText.innerHTML = "Medium Password";
    strengthText.style.color = "orange";
  } else {
    strengthText.innerHTML = "Weak Password";
    strengthText.style.color = "red";
  }
}

// Kiểm tra sự khớp nhau giữa mật khẩu và mật khẩu xác nhận
function checkPasswordMatch() {
  var password = document.getElementById("register-password").value;
  var confirmPassword = document.getElementById("confirm-password").value;
  var matchText = document.getElementById("password-match");

  if (
    password === confirmPassword &&
    password !== "" &&
    confirmPassword !== ""
  ) {
    matchText.innerHTML = "Passwords match";
    matchText.style.color = "green";
  } else {
    matchText.innerHTML = "Passwords do not match";
    matchText.style.color = "red";
  }
}

// Hiển thị form OTP
function showOtpForm() {
  document.getElementById("register-form").style.display = "none";
  document.getElementById("otp-form").style.display = "block";
}

// Validate Sign Up
function validateSignUp() {
  var password = document.getElementById("register-password").value;
  var confirmPassword = document.getElementById("confirm-password").value;
  var signUpButton = document.getElementById("sign-up-button");

  // Kiểm tra mật khẩu rỗng
  if (password === "" || confirmPassword === "") {
    alert("Please enter both password and confirm password.");
    return false;
  }

  // Kiểm tra sự khớp nhau giữa mật khẩu và mật khẩu xác nhận
  if (password !== confirmPassword) {
    alert("Passwords do not match!");
    return false; // Ngăn chặn việc submit form
  }

  // Vô hiệu hóa nút Sign up nếu mật khẩu không khớp
  signUpButton.disabled = false;

  return true; // Cho phép submit form nếu mật khẩu khớp nhau
}

// Lắng nghe sự kiện input trên các ô password
document
  .getElementById("register-password")
  .addEventListener("input", function () {
    checkPasswordStrength(); // Kiểm tra mật khẩu mạnh yếu
    checkPasswordMatch(); // Kiểm tra sự trùng khớp giữa mật khẩu và mật khẩu xác nhận
  });

document
  .getElementById("confirm-password")
  .addEventListener("input", function () {
    checkPasswordMatch(); // Kiểm tra sự trùng khớp giữa mật khẩu và mật khẩu xác nhận
  });
