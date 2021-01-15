        $(function() {
            $('div#room_act').click(function() {
            	$('#roomSearchBtn').removeClass('d-none');
            	$('#actSearchBtn').removeClass('d-none');
            	$('.smallBtn').addClass('d-none');
                $('#room').show();
                $('#room_act').addClass("nav_active");
                $('#act_act').removeClass("nav_active");
                $('#act').hide();
            });
            $('div#act_act').click(function() {
            	$('#roomSearchBtn').removeClass('d-none');
            	$('#actSearchBtn').removeClass('d-none');
            	$('.smallBtn').addClass('d-none');
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

            $('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
                $(this).val(picker.startDate.format('YYYY-MM-DD') + ' ~ ' + picker.endDate.format('YYYY-MM-DD'));
            });

            $('input[name="datefilter"]').on('cancel.daterangepicker', function(ev, picker) {
                $(this).val('');
            });
            //活動日曆
            $('input[name="datefilter1"]').daterangepicker({
                autoUpdateInput: false,
                singleDatePicker: true,
                minDate: new Date(),
                locale: {
                    daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    cancelLabel: 'Clear'
                }

            });
            $('input[name="datefilter1"]').on('apply.daterangepicker', function(ev, picker) {
                $(this).val(picker.startDate.format('YYYY-MM-DD'));
            });

            $('input[name="datefilter1"]').on('cancel.daterangepicker', function(ev, picker) {
                $(this).val('');
            });

            $(window).scroll(function() {
                if ($(window).scrollTop() >= 3) {
                	// 縮小
                	$('#roomSearchBtn').addClass('d-none');
                	$('#actSearchBtn').addClass('d-none');
                	$('.smallBtn').removeClass('d-none');
                    $('.header').addClass('upup');
                    $('.cc').hide();
                    $('.justify-content-md-center').addClass('shsh');
                    $('.header').removeClass('open');
                    $('.shsh').click(function() {
                        $('.header').removeClass('upup');
                        $('.header').addClass('open');
                    	$('#roomSearchBtn').removeClass('d-none');
                    	$('#actSearchBtn').removeClass('d-none');
                    	$('.smallBtn').addClass('d-none');
                        $('.cc').show();
                        $('.justify-content-md-center').removeClass('shsh');


                    });
                } else {
                	$('#roomSearchBtn').removeClass('d-none');
                	$('#actSearchBtn').removeClass('d-none');
                	$('.smallBtn').addClass('d-none');
                    $('.header').removeClass('upup');
                    $('.header').removeClass('open');
                    $('.cc').show();
                    $('.justify-content-md-center').removeClass('shsh');
                    $('.bigbig').css("margin-top", "0px");
                }
            });
            
        
            
            $(document).on('click', '#roomSearchBtn', function() {

            	$('#formRoom').submit();
            });
            
            $(document).on('click', '#actSearchBtn', function() {

            	$('#foract').submit();
            })




        });