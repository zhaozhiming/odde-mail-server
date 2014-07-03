describe('Controllers', function () {
    beforeEach(module('oms'));

    describe('RecipientsController', function () {
        var scope, controller, http;
        beforeEach(inject(function ($httpBackend, $rootScope, $controller) {
            http = $httpBackend;

            scope = $rootScope.$new();
            controller = $controller;
        }));

        it('should load the Recipients from the API', function () {
            http.expectGET('api/recipient/list').respond([
                {
                    username: "Tom",
                    email: "Tom@test.com"
                }
            ]);
            var ctrl = controller('RecipientsController', {$scope: scope});

            http.flush();
            expect(scope.recipients.length).toEqual(1);
            expect(scope.recipients[0].username).toEqual("Tom");
        });
    });

});
