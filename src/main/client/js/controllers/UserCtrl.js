'use strict';

angular.module('logicline.controllers')
.controller('UserCtrl', [
'$rootScope',
'$scope',
'apiUserService',

function($rootScope, $scope, apiUserService) {
  $scope.isOwnData = $scope.$state.current.name === 'customer_edit';
  $scope.referrer = $scope.isOwnData ? 'customer_search_view':'dashboard';

  apiUserService.getInformation($rootScope.searchUserIdFk || $rootScope.userIdFk).then(function(response) {
    $scope.userInformation = response;    
  });

  $scope.updateUser = function(isValid, userInformation) {
    if (isValid) {
      apiUserService.putInformation(userInformation).then(function(response) {
        $scope.updated = true;
      });
    };
  };

  $scope.resetPassword = function(userId) {
    apiUserService.passwordReset(userId).then(function(res) {    
        $scope.isReseted = true;   
        $scope.res = res;
    });
  };
  
  $scope.cancelEdit = function(userId) {
	delete $rootScope.searchUserIdFk;
	delete $rootScope.isSearchActive;		

	$scope.$state.go('dashboard');
};
}]);