window.hortaWeb.controller 'RoomController', ['$scope', 'RoomService', ($scope, roomService) ->
  roomService.get $scope
]