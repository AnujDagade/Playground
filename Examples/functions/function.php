<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Functions</title>

    <style>
        html {
            margin: 0%;
            padding: 0%;
        }

        body {
            background-color: #bfd1e2;
            margin: 0%;
            padding: 0%;
        }

        .title{
            font-size:20px;
        }

    </style>

</head>
<body>
    
        <div class="title">
            Value pass by reference
        </div>
        <?php

            $a = 69;

            echo "Before changing\n$a<br>";

            function chng(&$b)
            {
                $b *= 2;
            }

            chng($a);
            echo "After changing\n$a<br>";
        ?>


</body>
</html>