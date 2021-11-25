package pl.grizzlysoftware.dotykacka.client.v2.facade;

import com.fasterxml.jackson.databind.JsonNode;
import pl.grizzlysoftware.dotykacka.client.v2.model.ProductStock;
import pl.grizzlysoftware.dotykacka.client.v2.model.ResultPage;
import pl.grizzlysoftware.dotykacka.client.v2.model.Warehouse;
import pl.grizzlysoftware.dotykacka.client.v2.model.WarehouseItemSale;
import pl.grizzlysoftware.dotykacka.client.v2.model.WarehouseStockUp;
import pl.grizzlysoftware.dotykacka.client.v2.model.WarehouseTransfer;
import pl.grizzlysoftware.dotykacka.client.v2.service.WarehouseService;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class WarehouseServiceFacade extends DotykackaApiService<WarehouseService> {
    public WarehouseServiceFacade(WarehouseService service) {
        super(service);
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return createWarehouses(singletonList(warehouse))
                .stream()
                .findAny()
                .orElseThrow();
    }

    public Collection<Warehouse> createWarehouses(Collection<Warehouse> warehouses) {
        return execute(service.createWarehouses(warehouses));
    }
    
    public Collection<Warehouse> updateWarehouses(Collection<Warehouse> warehouses) {
        return execute(service.updateWarehouses(warehouses));
    }

    public Warehouse updateWarehouse(Warehouse warehouse) {
        return execute(service.updateWarehouse(warehouse.id, warehouse));
    }

    public Warehouse patchWarehouse(Warehouse warehouse) {
        return execute(service.patchWarehouse(warehouse.id, warehouse));
    }

    public Warehouse deleteWarehouse(Long warehouseId) {
        return execute(service.deleteWarehouse(warehouseId));
    }

    public Warehouse getWarehouse(Long id) {
        return execute(service.getWarehouse(id));
    }

    public ResultPage<Warehouse> getWarehouses(int page, int pageSize, String filter, String sort) {
        return execute(service.getWarehouses(page, pageSize, filter, sort));
    }

    public ResultPage<Warehouse> getWarehouses(int page, int pageSize, String sort) {
        return getWarehouses(page, pageSize, null, sort);
    }

    public Collection<Warehouse> getAllWarehouses(String sort) {
        return batchLoader.load(page -> getWarehouses(page.page, page.pageSize, sort));
    }

    public Collection<Warehouse> getAllWarehouses() {
        return getAllWarehouses(null);
    }

    public Collection<ProductStock> getProductStocks(Long warehouseId) {
        return execute(service.getProductStocks(warehouseId));
    }

    public ProductStock getProductStockById(Long warehouseId, Long productId) {
        return execute(service.getProductStockById(warehouseId, productId));
    }

    public JsonNode stockup(WarehouseStockUp body) {
        return execute(service.stockup(body.warehouseId, body));
    }

    public JsonNode transfer(WarehouseTransfer body) {
        return execute(service.transfer(body.warehouseId, body));
    }

    public JsonNode sales(WarehouseItemSale body) {
        return execute(service.sales(body.warehouseId, body));
    }
}
