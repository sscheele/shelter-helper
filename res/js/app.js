angular.module('main', [])
    .controller("MainCtrl", ["$scope", ($scope) => {
        markers = {};
        $scope.shelters = [];
        $scope.alert = (s)=>{alert(s);};
        $scope.currUser = null;
        $scope.toggleShelter = (shelter) => {
            //console.log(JSON.stringify(shelter));
            if (markers.hasOwnProperty(shelter.name)) {
                markers[shelter.name].setMap(null);
                delete markers[shelter.name];
            } else {
                markers[shelter.name] = new google.maps.Marker({
                    position: {lng: shelter.longitude, lat: shelter.latitude},
                    label: shelter.name,
                    map: map
                });
            }
        };
        // Initialize Firebase
        var config = {
            apiKey: "AIzaSyBHJWGNETFApjjO2EJKmwjBf8JM45AV7NM",
            authDomain: "shelter-helper.firebaseapp.com",
            databaseURL: "https://shelter-helper.firebaseio.com",
            projectId: "shelter-helper",
            storageBucket: "shelter-helper.appspot.com",
            messagingSenderId: "513616376080"
        };
        firebase.initializeApp(config);
        var database = firebase.database();
        var usersref = database.ref("user_info");
        firebase.auth().onAuthStateChanged(function (user) {
            if (user) {
                // User is signed in.
                //alert("Signed in as " + JSON.stringify(user));
                database.ref("user_info/" + user.uid).once('value', (snapshot) => {
                    if (!snapshot.val()) {
                        firebase.database().ref('user_info/' + user.uid).set({
                            "name": "Sam",
                            "key": user.uid,
                            "numReserved": 0,
                            "reservedAt": "",
                            "banned": false
                        });
                    } else {
                        $scope.currUser.numReserved = snapshot.val().numReserved;
                        $scope.currUser.reservedAt = snapshot.val().reservedAt;
                        $scope.currUser = snapshot.val();
                        $scope.$apply();
                    }
                });
                if (!user.emailVerified) {
                    user.sendEmailVerification().then(function () {
                        alert("We sent you an email to verify your account!");
                    }, function (error) {
                        alert("Email sending error!");
                    });
                }
                database.ref("shelters").on('value', (snapshot) => {
                    $scope.shelters = snapshot.val();
                    $scope.$apply();
                });
            } else {
                // User is signed out.
                // ...
            }
        });
        $scope.reserveShelter = (shelter) => {
            let tmp = {};
            Object.assign(tmp, shelter);
            tmp.capacity = tmp.capacity - 1;
            delete tmp["$$hashKey"];
            database.ref("shelters/"+shelter.key).set(tmp);
            $scope.currUser.reservedAt = shelter.name;
            $scope.currUser.numReserved = 1;
            database.ref("user_info/"+$scope.currUser.key).set($scope.currUser);
        }
        $scope.releaseShelter = (shelter) => {
            let tmp = {};
            Object.assign(tmp, shelter);
            tmp.capacity = tmp.capacity + 1;
            delete tmp["$$hashKey"];
            database.ref("shelters/"+shelter.key).set(tmp);
            $scope.currUser.reservedAt = "";
            $scope.currUser.numReserved = 0;
            database.ref("user_info/"+$scope.currUser.key).set($scope.currUser);
        }
    }]);