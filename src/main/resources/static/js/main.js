
$(document).ready(function(){

    $('.nBtn,.table .eBtn').on("click",function(event){
        event.preventDefault();
        var href= $(this).attr('href');
        var text = $(this).text();
        if(text=='Edit') {

            $.get(href, function (user, status) {
                $('.myForm #id').val(user.id);
                $('.myForm #name').val(user.name);
                $('.myForm #teamName').val(user.teamName);
                $('.myForm #salary').val(user.salary);
            });

            $('.myForm #exampleModal').modal();
        }else{
            $('.myForm #id').val('');
            $('.myForm #name').val('');
            $('.myForm #teamName').val('');
            $('.myForm #salary').val('');
            $('.myForm #exampleModal').modal();
        }
    });

    /*$('.table .dBtn').on('click',function(event){
        event.preventDefault();
        var href= $(this).attr('href');
        $('#myModel #delRef').attr('href',href);
        $('#myModel').model();
       });*/



});
