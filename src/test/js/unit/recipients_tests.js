describe('Controllers', function () {
    beforeEach(module('oms'));

    describe('RecipientsController', function () {
        var scope, controller, http;
        beforeEach(inject(function ($httpBackend, $rootScope, $controller) {
            http = $httpBackend;
            http.expectGET('api/recipient/list').respond([
                {
                    username: "Tom",
                    email: "Tom@test.com"
                }
            ]);

            scope = $rootScope.$new();
            controller = $controller('RecipientsController', {$scope: scope});
        }));

        it('should load the Recipients from the API', function () {
            http.flush();
            expect(scope.recipients.length).toEqual(1);
            expect(scope.recipients[0].username).toEqual("Tom");
        });

        it('should get the form setting from the controller', function () {
            expect(controller.setting).not.toBe(null);
        });
    });

});
