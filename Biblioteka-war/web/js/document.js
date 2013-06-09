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
});


function setActiveClass(active,toRemove){
   
   $(toRemove).removeClass('active');
   active.parent().addClass('active');
}