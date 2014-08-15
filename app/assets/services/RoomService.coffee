window.hortaWeb.factory 'RoomService', ($log, $http) ->
  get: (scope) ->
    $http.get 'room'
      .success (data, status, headers, config) ->
        $log.info('RoomService', 'success', data)
        scope.rooms = data
      .error (data, status, headers, config) ->
        $log.error('RoomService', 'error', data, status, headers, config)