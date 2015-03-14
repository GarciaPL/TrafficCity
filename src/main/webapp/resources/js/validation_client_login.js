$(document).ready(function () {
    var telephone = $("#telefon");
    var telephoneInfo = $("#telefonInfo");
    var sendButton = document.getElementById("send");

    //On blur
    telephone.blur(validatePhoneNumber);
    //On key press
    telephone.keyup(validatePhoneNumber);

    $(document).on("keypress", function (e) {
        validatePhoneNumber();
    });

    //validation functions
    function validatePhoneNumber() {
        if (document.getElementById("zgoda").checked == false) {
            telephone.addClass("ff_error");
            telephoneInfo.text("No agree to processing of the subscriber location!");
            telephoneInfo.addClass("f_error");
            sendButton.disabled = true;
            document.getElementById("send").style.backgroundColor = '#000000';
            return false;
        }
        var telefon_regex = /^\d{11}$/;
        if (isNaN(telephone.val())) {
            telephone.addClass("ff_error");
            telephoneInfo.text("Phone number is invalid. Only numbers!");
            telephoneInfo.addClass("f_error");
            sendButton.disabled = true;
            document.getElementById("send").style.backgroundColor = '#000000';
            return false;
        }
        if (!telefon_regex.test(telephone.val())) {
            telephone.addClass("ff_error");
            telephoneInfo.text("The phone number should consist of 11 digits (Country Code + Phone Number)!. Now it's " + telephone.val().length);
            telephoneInfo.addClass("f_error");
            sendButton.disabled = true;
            document.getElementById("send").style.backgroundColor = '#000000';
            return false;
        } else {
            telephone.removeClass("ff_error");
            telephoneInfo.text("");
            telephoneInfo.removeClass("f_error");
            sendButton.disabled = false;
            document.getElementById("send").style.backgroundColor = '#085299';
            return true;
        }
    }
});