window.hortaWeb.controller 'LogController', ['$scope', '$stateParams', '$log', 'LogService',
  ($scope, $stateParams, $log, logService) ->
    CALLBACK_INTERVAL = 5000

    { roomId } = $stateParams

    callback = (timestamp) ->
      logService.append roomId, timestamp
        .success (data, status, headers, config) ->
          $log.info('LogService', 'success', data)
          for log in data.logs
            $scope.logs.push log

          window.setTimeout callback, CALLBACK_INTERVAL, [timestamp]
        .error (data, status, headers, config) ->
          $log.error('LogService', 'error', data, status, headers, config)

    logService.get roomId
      .success (data, status, headers, config) ->
        $log.info('LogService', 'success', data)
        $scope.logs = data.logs
        window.setTimeout callback, CALLBACK_INTERVAL, [data.timestamp]
      .error (data, status, headers, config) ->
        $log.error('LogService', 'error', data, status, headers, config)

]