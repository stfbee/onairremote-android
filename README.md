# On Air Display
v 1.0.0

## Что это?
Дисплей для отображения текущей голосовй сессии по зуму/дискорду/прочему скайпу.

Работает в связке с https://github.com/stfbee/onairremote-winendpoint

## Как собрать?
Проверь, что в `MainViewModel` указан адрес твоего компа, порт 4200 - такой же, на котором слушает ендпоинт.

```gradlew assempleDebug```

Устанавливай `app\build\outputs\apk\debug\app-debug.apk` на ненужную мобилу (или планшет) и вешай её на свою дверь.

## TODO
1. научится искать сервер в локальной сети на манер IoT
2. почистить код
3. добавить настройки для выбора адреса/стиля/етц