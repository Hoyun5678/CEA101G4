$(function () {

    // ===== click button to details
    $('button.b2').click(function () {
        window.location.href = "./accom.html";
    })

    $('li.detail').click(function () {
        window.location.href = "./glamping.html";
    })

    $('div.marker').click(function () {
        window.location.href = "./intro.html";
    });



    $('div#room_act').click(function () {
        $('#room').show();
        $('#room_act').addClass("nav_active");
        $('#act_act').removeClass("nav_active");
        $('#act').hide();
    });
    $('div#act_act').click(function () {
        $('#act').show();
        $('#act_act').addClass("nav_active");
        $('#room_act').removeClass("nav_active");
        $('#room').hide();
    });
    //訂房日曆
    $('input[name="datefilter"]').daterangepicker({
        autoUpdateInput: false,
        minDate: new Date(),
        locale: {
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            cancelLabel: 'Clear'
        }
    });

    $('input[name="datefilter"]').on('apply.daterangepicker', function (ev, picker) {
        $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
    });

    $('input[name="datefilter"]').on('cancel.daterangepicker', function (ev, picker) {
        $(this).val('');
    });
    //活動日曆
    $('input[name="datefilter1"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        minDate: new Date(),
        locale: {
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            cancelLabel: 'Clear'
        }

    });




    $(window).scroll(function () {
        if ($(window).scrollTop() >= 300) {
            $('.header').addClass('upup');
            $('.cc').hide();
            $('.justify-content-md-center').addClass('shsh');
            $('.header').removeClass('open');
            $('.shsh').click(function () {
                $('.header').removeClass('upup');
                $('.header').addClass('open');
                $('.cc').show();
                $('.justify-content-md-center').removeClass('shsh');
            });
        } else {
            $('.header').removeClass('upup');
            $('.header').removeClass('open');
            $('.cc').show();
            $('.justify-content-md-center').removeClass('shsh');
        }
    });



    // 點按地點等等的框框 input也可以得到焦點
    $('.align-self-center').click(function () {
        $(this).children('input').focus();
    });
    
    $(document).on('click', '#roomSearchBtn', function() {
    	alert('');
    	$('#formRoom').submit();
    })

});