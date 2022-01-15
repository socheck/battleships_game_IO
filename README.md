# battleships_game_IO

Elegancki projekt statków na zaliczenie Inżynierii Oprogramowania :)

Proszę dodać /lib z JavaFX w project structure

vm start option do main: 

Podmienić ścieżkę do swojego javafx

```--module-path "D:\JavaFX\javafx-sdk-15.0.1\lib" --add-modules=javafx.graphics,javafx.web,javafx.fxml,javafx.media,javafx.web --add-reads javafx.graphics=ALL-UNNAMED --add-opens javafx.controls/com.sun.javafx.charts=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio.common=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.css=ALL-UNNAMED --add-opens javafx.base/com.sun.javafx.runtime=ALL-UNNAMED```

Dodać libs z jacksonami i sqlite, które są w folderze \battleships_IO\bazaVol1\libs_baza jako aplikacje jar do projektu

Opcje VM do poszczególnych libsów z bazy:

```--module-path "D:\battleships_IO\bazaVol1\libs_baza\jackson-core-2.9.9.jar" --add- ```

```--module-path "D:\battleships_IO\bazaVol1\libs_baza\jackson-annotations-2.9.0.jar" --add- ```

```--module-path "D:\battleships_IO\bazaVol1\libs_baza\jackson-databind-2.9.3.jar" --add- ```




