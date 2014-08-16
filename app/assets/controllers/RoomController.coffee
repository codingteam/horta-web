window.hortaWeb.controller 'RoomController', ['$scope', '$log', 'RoomService', ($scope, $log, roomService) ->
  roomService.get $scope
    .success (data, status, headers, config) ->
      $log.info('RoomService', 'success', data)
      $scope.rooms = data
    .error (data, status, headers, config) ->
      $log.error('RoomService', 'error', data, status, headers, config)
]