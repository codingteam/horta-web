window.hortaWeb = angular.module('hortaWeb', ['ui.router'])
  .config ($stateProvider, $urlRouterProvider) ->
    $urlRouterProvider.otherwise("/room")
    $stateProvider
      .state 'rooms',
        url: '/room'
        templateUrl: 'assets/views/rooms.html'
      .state 'logs',
        url: '/room/:roomId'
        templateUrl: 'assets/views/logs.html'
