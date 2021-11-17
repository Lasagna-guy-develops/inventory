<?php
mysql_connect("remotemysql.com", "varDt01HvK", "gQa4TK4SvA");
mysql_select_db("varDt01HvK");

$q=mysql_query("SELECT * FROM Inventory");
while($e=mysql_fetch_assoc($q))
        $output[]=$e;

print(json_encode($output));

mysql_close();
?>