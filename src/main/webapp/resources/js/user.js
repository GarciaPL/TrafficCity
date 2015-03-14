function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

function loadRegister() {
    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/register';
        },
        error: function (e) {

        }
    });
}

function loadFileUploader() {
    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/uploadfile';
        },
        error: function (e) {

        }
    });
}

function loadSettings() {
    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/settings';
        },
        error: function (e) {

        }
    });
}

function loadHeatMaps() {
    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/moderator/edit';
        },
        error: function (e) {
        }
    });
}

function loadUserMarkers() {
    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/user_markers';
        },
        error: function (e) {
        }
    });
}

function loadIndex() {
    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/index';
        },
        error: function (e) {
        }
    });
}

function loadGenerateGallery(project) {

    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/generate/gallery/' + project;
        },
        error: function (e) {

        }
    });
}

function loadGallery(project) {

    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/gallery/' + project;
        },
        error: function (e) {

        }
    });
}

function changeProperty(propName, propValue) {
    var property = $("#propValue_" + propName);
    var changer = $("#change_" + propName);

    if (property.attr('readonly') != undefined) {
        property.prop('readonly', false);
        property.attr('style', "background-color", "none");
        changer.val('Save');
    } else {
        property.prop('readonly', true);
        property.css("background-color", "gainsboro");
        changer.val('Change');

        $.ajax({
            type: "POST",
            url: contextPath + "/configapi/changeprop",
            data: {
                propName: propName,
                propValue: propValue
            },
            success: function (response) {
                if (response.result === "SUCCESS") {
                    location.reload();
                } else {
                    errorInfo = "<br/><div align=\"center\" style=\"color : red;\">";
                    errorInfo = errorInfo + response.message + "<br/>";
                    errorInfo = errorInfo + "</div><br/>";
                    $('#propertyMessage').html(errorInfo);
                    $('#propertyMessage').show('slow');
                }
            },
            error: function (e) {
            }
        });
    }
}

function loadDashboard() {
    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/dashboard';
        },
        error: function (e) {

        }
    });
}

function loadProjects() {
    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/projects';
        },
        error: function (e) {

        }
    });
}

function loadLogs() {
    $.ajax({
        type: "GET",
        success: function (response) {
            window.location.href = contextPath + '/logs';
        },
        error: function (e) {

        }
    });
}

function loginClient() {
    var phoneNumberForm = $('#telefon').val();

    $.ajax({
        type: "POST",
        url: contextPath + "/login",
        data: {
            phoneNumber: phoneNumberForm
        },
        success: function (response) {
            if (response.result === "SUCCESS") {
                window.location.href = contextPath + '/dashboard';
            } else {
                errorInfo = "<br/><div align=\"center\" style=\"color : red;\">";
                errorInfo = errorInfo + response.message + "<br/>";
                errorInfo = errorInfo + "</div><br/>";
                $('#loginMessage').html(errorInfo);
                $('#loginMessage').show('slow');
            }
        },
        error: function (e) {
        }
    });

}

function registerClient() {

    var telephoneForm = $('#telefon').val();

    $.ajax({
        type: "POST",
        url: contextPath + "/register",
        data: {
            phoneNumber: telephoneForm
        },
        success: function (response) {
            if (response.result === "SUCCESS") {
                window.location.href = contextPath + '/dashboard';
            } else {
                errorInfo = "<div align=\"center\" style=\"color : red;\">";
                errorInfo = errorInfo + response.message + "<br/>";
                errorInfo = errorInfo + "</div><br/>";
                $('#errorRegistrationAccount').html(errorInfo);
                $('#errorRegistrationAccount').show('slow');
            }
        },
        error: function (e) {
            alert('Error: ' + e.message.toSource());

        }
    });
}