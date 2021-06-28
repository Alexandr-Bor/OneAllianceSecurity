jQuery(function() {
    "use strict";

    const listObj = $('#all_objects_security li');

    /**
     * Form Search, Input min 2 liters. Search automatic.
     */
    $(document).on('input', 'input:text', function() {
        let findText = $("input[type='text']")[0].value; // искомая фраза

        if ( findText.length > 1 ) // Ищем когда введено от двух символов
            searchElementInLists( findText );
        if ( findText.length === 0 ) // При удалении последнего символа, показывать весь список
            $('#all_objects_security').html(listObj);
    });


    /**
     * Function for Search Objects in find text
     */
    function searchElementInLists( findText ) {

        // findText = findText.toLowerCase();
        let findElements = []; // массив найденых элементов

        // Обход по объекту
        listObj.each( function (num, elem) {
            // Обход по элемета
            for ( let key in elem ) {

                // Выбираем только текст
                if ( key === 'innerText') {
                    let str = elem[key].toLowerCase();
                    // поиск подстроки в строке
                    if( str.indexOf( findText.toLowerCase() ) > -1 ) {
                        findElements.push( elem ); // складываем найденое в массив
                    }
                }
            }
        });
        if ( !findElements.length ) { // если нет найденных элементов, очищаем список
            $('#all_objects_security').html('');
        } else {
            $('#all_objects_security').html('');
            // Выводим найденные элементы на экран
            findElements.forEach( (el) => {
                $('#all_objects_security').append(el);
            });
        }
    }

    // Очистка поля ввода с восстановлением списка
    $('#clear_form').on('click', function (e) {
        $("input[type='text']")[0].value = '';
        $('#all_objects_security').html('');
        $('#all_objects_security').html(listObj);
    })

    /* Scroll down - show navigation, scrolls up - hide navigation */
    // Прятаем/показываем шапку
    let prevScrollpos = window.pageYOffset;
    let mainMenu = document.getElementById("header_section");
    window.onscroll = function() {
        let currentScrollPos = window.pageYOffset;
        if (prevScrollpos < currentScrollPos) mainMenu.style.marginTop = "-75px";
        else mainMenu.style.marginTop = "0px";
        prevScrollpos = currentScrollPos;
    }

});

