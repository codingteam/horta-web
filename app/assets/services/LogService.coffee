window.hortaWeb.factory 'LogService', ($log, $http) ->
  get: (roomId) ->
    $http.get "room/#{roomId}/log"

  append: (roomId, timestamp) ->
    $http.get "room/#{roomId}/log/#{timestamp}"