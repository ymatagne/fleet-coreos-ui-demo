angular.module('uiFleetCoreOs.uiFleetCoreOsCtrl', ['ngDialog'])
    .controller('UiFleetCoreOsCtrl', function ($interval,ngDialog) {
        var ctrl = this;
        ctrl.tatooine = null;
        ctrl.kamino = null;
        ctrl.coruscant = null;
        ctrl.naboo = null;
        ctrl.alderaan = null;
        ctrl.hoth = null;


        var tatooine = new SockJS('/tatooine');
        tatooineClient = Stomp.over(tatooine);
        tatooineClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            tatooineClient.subscribe('/planets/tatooine', function (planet) {
                ctrl.tatooine = JSON.parse(planet.body);
            });
            $interval(function () {
                tatooineClient.send("/app/tatooine", {});
            }, 2000);
        });

        var kamino = new SockJS('/kamino');
        kaminoClient = Stomp.over(kamino);
        kaminoClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            kaminoClient.subscribe('/planets/kamino', function (planet) {
                ctrl.kamino = JSON.parse(planet.body);
            });
            $interval(function () {
                kaminoClient.send("/app/kamino", {});
            }, 2000);
        });

        var coruscant = new SockJS('/coruscant');
        coruscantClient = Stomp.over(coruscant);
        coruscantClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            coruscantClient.subscribe('/planets/coruscant', function (planet) {
                ctrl.coruscant = JSON.parse(planet.body);
            });
            $interval(function () {
                coruscantClient.send("/app/coruscant", {});
            }, 2000);
        });

        var naboo = new SockJS('/naboo');
        nabooClient = Stomp.over(naboo);
        nabooClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            nabooClient.subscribe('/planets/naboo', function (planet) {
                ctrl.naboo = JSON.parse(planet.body);
            });
            $interval(function () {
                nabooClient.send("/app/naboo", {});
            }, 2000);
        });

        var alderaan = new SockJS('/alderaan');
        alderaanClient = Stomp.over(alderaan);
        alderaanClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            alderaanClient.subscribe('/planets/alderaan', function (planet) {
                ctrl.alderaan = JSON.parse(planet.body);
            });
            $interval(function () {
                alderaanClient.send("/app/alderaan", {});
            }, 2000);
        });

        var hoth = new SockJS('/hoth');
        hothClient = Stomp.over(hoth);
        hothClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            hothClient.subscribe('/planets/hoth', function (planet) {
                ctrl.hoth = JSON.parse(planet.body);
            });
            $interval(function () {
                hothClient.send("/app/hoth", {});
            }, 2000);
        });

        ctrl.range = function (planet) {
            var ratings = [];
            if (planet !==null && planet.troopers !== null && planet.troopers.length > 0) {
                for (var i = 0; i < planet.troopers.length; i++) {
                    ratings.push(i)
                }
            }
            return ratings;
        };
        ctrl.showPlanet = function (planet) {
            if (planet !== undefined && planet !== null) {
                return planet.up;
            } else {
                return false;
            }
        };
        ctrl.showDeadPlanet = function (planet) {
            if (planet !== undefined && planet !== null) {
                return planet.up;
            } else {
                return true;
            }
        };

        ctrl.openPopin = function(){
            ngDialog.open({
                template: 'templates/popin.html',
                className: 'ngdialog-theme-plain'
            });
        };
    });