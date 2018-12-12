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
    data: { isLoggedIn: false }
});


Vue.component('login-component',{
        template: '<div class="topnav">'
        	+ '<a href="/home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a>'
        	+ '<a href="/"><span class="glyphicon glyphicon-cd" aria-hidden="true"></span> Artists</a>'
        	+ '<a href="/music"><span class="glyphicon glyphicon-headphones" aria-hidden="true"></span> Music</a>'
        	+ '<a href="/impressum"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Impressum</a>'
        	+ '<a v-if=!isLoggedIn() href="/login"> <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span></a>'
        	+ '<a v-if=isLoggedIn() v-on:click="navigateSettings"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span> Settings</a>'
        	+ '<a v-if=isLoggedIn() v-on:click="logOut"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout ({{logged_in_msg}})</a>'
        	+ '</div>',
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
        },
        navigateSettings() {
            document.location.replace("/private?access_token=" + getCookie("access_token"));        	
        }
    }
});