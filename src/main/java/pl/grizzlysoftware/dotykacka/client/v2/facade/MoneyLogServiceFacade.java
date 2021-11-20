package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.MoneyLog;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.MoneyLogService;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class MoneyLogServiceFacade extends DotykackaApiService<MoneyLogService> {

    public MoneyLogServiceFacade(MoneyLogService service) {
        super(service);
    }

    public MoneyLog getMoneyLog(Long id) {
        return execute(service.getMoneyLog(id));
    }

    public ResultPage<MoneyLog> getMoneyLogs(int page, int pageSize, String filter, String sort) {
        return execute(service.getMoneyLogs(page, pageSize, filter, sort));
    }

    public ResultPage<MoneyLog> getMoneyLogs(int page, int pageSize, String sort) {
        return getMoneyLogs(page, pageSize, null, sort);
    }

    public Collection<MoneyLog> getAllMoneyLogs(String sort) {
        return batchLoader.load(page -> getMoneyLogs(page.page, page.pageSize, sort));
    }

    public Collection<MoneyLog> getAllMoneyLogs() {
        return getAllMoneyLogs(null);
    }
}
