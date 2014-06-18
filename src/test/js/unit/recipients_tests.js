toastr = {
    success: function (msg) {
        console.log(msg);
    }
};

describe('Controllers', function () {
    beforeEach(module('oms'));

    beforeEach(function () {
        this.addMatchers({
            toEqualData: function (expected) {
                return angular.equals(this.actual, expected);
            }
        });
    });

    describe('RecipientsController', function () {
        var scope, controller, http;
        beforeEach(inject(function ($httpBackend, $rootScope, $controller) {
            http = $httpBackend;
            http.expectPOST('/api/recipient/list').respond([
                {
                    username: "Tom",
                    email: "Tom@test.com"
                }
            ]);
            scope = $rootScope.$new();
            controller = $controller(SendController, {$scope: scope, $http: http});
        }));

        it('should load the Recipients from the API', function () {
            expect(scope.recipients).toEqual([]);
            http.flush();
            expect(scope.recipients.length).toEqual(1);
            expect(scope.recipients[0].username).toEqual("Tom");
        });
    });

});
