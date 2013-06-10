$(document).ready(function(){
   $('.show-comments').bind("click",function(){
      $('.comments').toggle(200);       
   });
   
   $('.about-history-click').click(function(){
       $('.about-map').hide();
       $('.about-history').show();
       setActiveClass($(this),'.nav li');
   });
   
   $('.about-map-click').click(function(){
       $('.about-map').show();
       $('.about-history').hide();
       setActiveClass($(this),'.nav li');
   });
   
   
   (function(){
       
       switch(window.action){
           case "/glowna.xhtml":
           case "/wydarzenia.xhtml":    
               $('.active-glowna').addClass('active');
               break;
           case "/about.xhtml":
               
               $('.active-about').addClass('active');
               break;
           case "/catalog.xhtml":
           case "/searchresult.xhtml":    
               $('.active-catalog').addClass('active');
               break;
               
           case "/editUsers.xhtml":
               $('.active-users').addClass('active');
               break;
           case "/addNews.xhtml":
               $('.active-news').addClass('active');
               break ;
           case "/addBook.xhtml":
               $('.active-addbook').addClass('active');
               break ;   
           case "/profile.xhtml":
               $('.active-profile').addClass('active');
               break ;   
           default :
               $('.active-glowna').addClass('active');
               break;
       }
   })();
   
});


function setActiveClass(active,toRemove){
   
   $(toRemove).removeClass('active');
   active.parent().addClass('active');
}