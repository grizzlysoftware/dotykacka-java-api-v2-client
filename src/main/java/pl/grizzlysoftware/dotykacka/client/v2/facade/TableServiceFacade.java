package pl.grizzlysoftware.dotykacka.client.v2.facade;

import pl.grizzlysoftware.dotykacka.client.v2.model.Table;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.service.TableService;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class TableServiceFacade extends DotykackaApiService<TableService> {

    public TableServiceFacade(TableService service) {
        super(service);
    }

    public Table getTable(Long id) {
        return execute(service.getTable(id));
    }

    public ResultPage<Table> getTables(int page, int pageSize, String filter, String sort) {
        return execute(service.getTablees(page, pageSize, filter, sort));
    }

    public ResultPage<Table> getTables(int page, int pageSize, String sort) {
        return getTables(page, pageSize, null, sort);
    }

    public Collection<Table> getAllTables(String sort) {
        return batchLoader.load(page -> getTables(page.page, page.pageSize, sort));
    }

    public Collection<Table> getAllTables() {
        return getAllTables(null);
    }
}
