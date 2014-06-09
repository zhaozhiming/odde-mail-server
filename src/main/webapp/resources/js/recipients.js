function RecipientsController($scope, $http, $route) {
    var rules = {
        userNameKeyword: {
            identifier: 'username',
            rules: [
                {
                    type: 'empty',
                    prompt: '请输入姓名'
                },
                {
                    type: 'maxLength[100]',
                    prompt: '姓名长度不能超过100个字符'
                }
            ]
        },
        emailKeyword: {
            identifier: 'email',
            rules: [
                {
                    type: 'empty',
                    prompt: '请输入Email'
                },
                {
                    type: 'email',
                    prompt: '请输入一个有效的email地址'
                }
            ]
        }
    };

    var setting = {
        onSuccess: function () {
            var addData = {
                "username": $("#username").val() || "",
                "email": $("#email").val() || ""
            };

            $scope.add = function () {
                $http.post('api/recipient/add', addData, config).success(function (data) {
                    $("#addResult").modal('setting', {
                        onHidden: function () {
                            $route.reload();
                        }
                    }).modal('show');
                    $scope.result = data;
                });
            };
        }
    };

    $('#addRecipientsForm').form(rules, setting);

    $http.get('api/recipient/list').success(function (data) {
        $scope.recipients = data;
    });
}
