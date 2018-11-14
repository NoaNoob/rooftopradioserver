function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

function set_cookie(name, value) {
    document.cookie = name +'='+ value +'; Path=/;';
}
function delete_cookie(name) {
    document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

window.Event = new Vue({
    data: {isLoggedIn: false}
});


Vue.component('login-component',{
//    template: '<ul class="navMenu" v-if="!isLoggedIn()" style="float:right"><li style="margin-right:5px"><a href="/login">Login</a></li></ul>' +
//    '<p v-else id="loggedIn">{{logged_in_msg}} <span v-on:click="logOut" v-show="isLoggedIn()"><b color="blue">Logout</b></span></p>',
    template: '<ul class="nav navbar-nav"><li><a href="/">Home</a></li>'
    	+ '<li v-if=!isLoggedIn()><a href="/login">Login</a></li>'
    	+ '<li v-if=isLoggedIn()><a v-on:click="logOut">Logout ({{logged_in_msg}})</a></li></ul>',
    data: function(){
        return {logged_in_msg : ""}
    },
    mounted(){
        if(getCookie("access_token")){
            axios.get("/getUsername?access_token=" + getCookie("access_token"))
                .then(function(response){
                    this.logged_in_msg = response.data;
                    window.Event.isLoggedIn = true;
                    Event.$emit('logged-in');
                }.bind(this))
                .catch(function(error){
                    delete_cookie("access_token");
                    return error;
                });
        }
    },
    methods : {
        logOut(){
            axios.get("/logout?access_token="+getCookie("access_token"))
                .then(function(response){
                    window.Event.isLoggedIn = false;
                    this.logged_in_msg  = "Successfully logged out";
                    delete_cookie("access_token")
                }.bind(this))
        },
        isLoggedIn(){
            return window.Event.isLoggedIn;
        }
    }
});