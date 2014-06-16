function isValidEmailAddress(emailAddress) {
    var reg = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    return emailAddress.match(reg) != null;
}

function SendController($scope, $http, $route) {
    $.fn.form.settings.rules.emails = function (value) {
        var emails = value.split(",");
        for(var i = 0; i < emails.length; i++) {
            if(!isValidEmailAddress(emails[i])) {
                return false;
            }
        }
        return true;
    };

    var rules = {
        recipientsKeyword: {
            identifier: 'recipients',
            rules: [
                {
                    type: 'empty',
                    prompt: '请输入收件人'
                },
                {
                    type: 'emails',
                    prompt: '请输入有效的email地址'
                }
            ]
        },
        subjectKeyword: {
            identifier: 'subject',
            rules: [
                {
                    type: 'empty',
                    prompt: '请输入主题'
                },
                {
                    type: 'maxLength[100]',
                    prompt: '主题长度不能超过100个字符'
                }
            ]
        }
    };

    var setting = {
        onSuccess: function () {
            var sendData = {
                "recipients": $("#recipients").val() || "",
                "subject": $("#subject").val() || "",
                "content": $("#content").val() || ""
            };

            $scope.send = function () {
                $http.post('api/mail/send', sendData, config).success(function (data) {
                    $("#sendResult").modal('setting', {
                        onHidden: function () {
                            $route.reload();
                        }
                    }).modal('show');
                    $scope.result = data;

                });
            };
        }
    };

    $('#sendForm').form(rules, setting);
}