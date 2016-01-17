angular.module('uiFleetCoreOs.popinCtrl', [])
    .controller('PopinCtrl', function () {
        var ctrl = this;
        ctrl.nbrTroopers = 0;
        ctrl.randomTrooper = 0;

        ctrl.third = null;
        ctrl.second = null;
        ctrl.first = null;

        ctrl.random = function () {
            ctrl.randomTrooper = Math.floor((Math.random() * ctrl.nbrTroopers) + 1);
        };
        ctrl.validate = function () {
            if (!ctrl.third) {
                ctrl.third = ctrl.randomTrooper;
            } else if (!ctrl.second) {
                ctrl.second = ctrl.randomTrooper;
            } else if (!ctrl.first) {
                ctrl.first = ctrl.randomTrooper;
            }
        };
    });