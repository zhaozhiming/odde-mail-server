function SendController($scope, $http, $route) {
    var rules = {
        recipientsKeyword: {
            identifier: 'recipients',
            rules: [
                {
                    type: 'empty',
                    prompt: '请输入收件人'
                },
                {
                    type: 'email',
                    prompt: '请输入一个有效的email地址'
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
                    type   : 'maxLength[100]',
                    prompt : '主题长度不能超过100个字符'
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
                        onHidden : function() {
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