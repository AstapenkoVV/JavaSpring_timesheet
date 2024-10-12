package ru.gb.timesheet;

public class REST {

    /**
     * HTTP - протокол
     * gRPC - протокол
     * WebSockets - протокол
     *
     * Путь\эндпоинт\ручка\ресурс - /timesheet
     *
     * REST - набор соглашений, как оформлять\проектировать ресурсы вашего сервиса
     * 1. НЕ использовать глаголы в путях
     * /deleteTimesheet/{id} - плохо
     * DELETE /timesheet/{id} - хорошо
     *
     * 2. Ресурсы должны вкладываться друг в друга
     * GET /users/{userId}/roles/{roleId} - получить РОЛЬ с идентификатором roleId у юзера userId
     * GET /roles/{id}
     * GET /department/{id}/employees/{id}/head/consultant/roles/{id}
     *
     * Получить юзеров, у которых имя содердит какую-то подстроку
     * GET /users?name-like="ascaafdaf"               -> 204 No Connect
     * GET /users?sort-by=age&sort-order=desc    - сортировка пользователей по возрасту, по убыванию
     *
     * 3. Рекомендация: использовать множественное число для ресурсов
     * Вместо /user использовать /users
     *
     * 4. Слова внутри ресурса соединяюься дефисом
     * GET github.com/project/pull-requests/{id}
     *
     */
}
