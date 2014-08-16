window.hortaWeb.factory 'RoomService', ($log, $http) ->
  get: ->
    $http.get 'room'
