angular.module('uiFleetCoreOs', [])
    .controller('UiFleetCoreOsCtrl', function($interval) {
        var ctrl = this;
        ctrl.messages = [];


        var socket = new SockJS('/etcd');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/traffic/planets', function(planet){
                ctrl.messages.push(JSON.parse(planet.body).name);
            });
            $interval(function(){
                stompClient.send("/app/etcd", {});
            }, 5000);
        });

    });