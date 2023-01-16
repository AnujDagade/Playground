<?php

    $email = "drcool4200@gmail.com";

    if(mail($email,"hi","This is body"))
    {
        echo "Messeage send";
    }
    else
        echo 'failed';

?>