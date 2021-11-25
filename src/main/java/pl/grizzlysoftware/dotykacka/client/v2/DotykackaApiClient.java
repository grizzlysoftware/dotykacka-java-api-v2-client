/*
 * Copyright (c) 2021 Grizzly Software, https://grizzlysoftware.pl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package pl.grizzlysoftware.dotykacka.client.v2;

import pl.grizzlysoftware.dotykacka.client.v2.facade.AuthenticationServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.BranchServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.CategoryServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.CloudServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.CustomerServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.DeliveryNoteServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.DiscountGroupServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.EetSubjectServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.EmployeeServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.MoneyLogServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.OrderItemServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.OrderServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.ProductCustomizationServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.ProductIngredientServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.ProductServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.ReservationServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.SupplierServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.TableServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.TagServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.WarehouseBranchServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.facade.WarehouseServiceFacade;
import pl.grizzlysoftware.dotykacka.client.v2.service.AuthenticationService;
import pl.grizzlysoftware.dotykacka.client.v2.service.BranchService;
import pl.grizzlysoftware.dotykacka.client.v2.service.CategoryService;
import pl.grizzlysoftware.dotykacka.client.v2.service.CloudService;
import pl.grizzlysoftware.dotykacka.client.v2.service.CustomerService;
import pl.grizzlysoftware.dotykacka.client.v2.service.DeliveryNoteService;
import pl.grizzlysoftware.dotykacka.client.v2.service.DiscountGroupService;
import pl.grizzlysoftware.dotykacka.client.v2.service.EetSubjectService;
import pl.grizzlysoftware.dotykacka.client.v2.service.EmployeeService;
import pl.grizzlysoftware.dotykacka.client.v2.service.MoneyLogService;
import pl.grizzlysoftware.dotykacka.client.v2.service.OrderItemService;
import pl.grizzlysoftware.dotykacka.client.v2.service.OrderService;
import pl.grizzlysoftware.dotykacka.client.v2.service.ProductCustomizationService;
import pl.grizzlysoftware.dotykacka.client.v2.service.ProductIngredientService;
import pl.grizzlysoftware.dotykacka.client.v2.service.ProductService;
import pl.grizzlysoftware.dotykacka.client.v2.service.ReservationService;
import pl.grizzlysoftware.dotykacka.client.v2.service.SupplierService;
import pl.grizzlysoftware.dotykacka.client.v2.service.TableService;
import pl.grizzlysoftware.dotykacka.client.v2.service.TagService;
import pl.grizzlysoftware.dotykacka.client.v2.service.WarehouseBranchService;
import pl.grizzlysoftware.dotykacka.client.v2.service.WarehouseService;
import pl.grizzlysoftware.dotykacka.util.AccessTokenProvider;
import pl.grizzlysoftware.dotykacka.util.ETagCloudEntityApplier;
import pl.grizzlysoftware.dotykacka.util.ETagRequestHeaderApplier;
import pl.grizzlysoftware.dotykacka.util.TokenRenewingRequestInterceptor;
import pl.grizzlysoftware.util.CompositeOnRetroCallExecutionListener;
import pl.grizzlysoftware.util.OkHttpLoggingInterceptor;

import java.util.List;

import static java.lang.String.format;
import static pl.grizzlysoftware.dotykacka.util.exception.ExceptionPreconditions.checkNotNull;
import static pl.grizzlysoftware.util.OkHttpClientUtils.builder;
import static pl.grizzlysoftware.util.RetrofitUtils.service;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 * <p>
 * Dotykacka API V2 client class
 */
public class DotykackaApiClient {
    public final AuthenticationService authenticationService;
    public final AuthenticationServiceFacade authenticationServiceFacade;

    public final CloudServiceFacade cloudServiceFacade;
    public final BranchServiceFacade branchServiceFacade;
    public final CategoryServiceFacade categoryServiceFacade;
    public final CustomerServiceFacade customerServiceFacade;
    public final DiscountGroupServiceFacade discountGroupServiceFacade;
    public final DeliveryNoteServiceFacade deliveryNoteServiceFacade;
    public final EmployeeServiceFacade employeeServiceFacade;
    public final MoneyLogServiceFacade moneyLogServiceFacade;
    public final OrderServiceFacade orderServiceFacade;
    public final OrderItemServiceFacade orderItemServiceFacade;
    public final ProductServiceFacade productServiceFacade;
    public final ProductCustomizationServiceFacade productCustomizationServiceFacade;
    public final ProductIngredientServiceFacade productIngredientServiceFacade;
    public final ReservationServiceFacade reservationServiceFacade;
    public final SupplierServiceFacade supplierServiceFacade;
    public final TableServiceFacade tableServiceFacade;
    public final TagServiceFacade tagServiceFacade;
    public final WarehouseServiceFacade warehouseServiceFacade;
    public final WarehouseBranchServiceFacade warehouseBranchServiceFacade;
    public final EetSubjectServiceFacade eetSubjectServiceFacade;

    public DotykackaApiClient(ApiConfiguration configuration) {
        checkNotNull(configuration, "20211106:084700", "ApiConfiguration cannot be null");
        final var httpClient = builder()
                .callTimeout(configuration.requestTimeout)
                .addInterceptor(new OkHttpLoggingInterceptor())
                .build();

        authenticationService = service(httpClient, configuration.url, AuthenticationService.class);
        authenticationServiceFacade = new AuthenticationServiceFacade(
                authenticationService,
                configuration.cloudId,
                configuration.refreshToken
        );

        final var reqHttpClient = builder()
                .callTimeout(configuration.requestTimeout)
                .addInterceptor(new TokenRenewingRequestInterceptor(new AccessTokenProvider(authenticationServiceFacade)))
                .addInterceptor(new ETagRequestHeaderApplier())
                .addInterceptor(new OkHttpLoggingInterceptor())
                .build();

        final var compositeExecutionListener = new CompositeOnRetroCallExecutionListener(List.of(
                new ETagCloudEntityApplier()
        ));

        final var cloudServiceUrl = cloudServiceUrl(configuration.url);
        final var cloudService = service(reqHttpClient, cloudServiceUrl, CloudService.class);
        cloudServiceFacade = new CloudServiceFacade(cloudService);

        final var apiUrl = cloudServiceUrl + "/"+ configuration.cloudId;

        final var branchService = service(reqHttpClient, branchServiceUrl(apiUrl), BranchService.class);
        branchServiceFacade = new BranchServiceFacade(branchService);
        branchServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var categoryService = service(reqHttpClient, categoryServiceUrl(apiUrl), CategoryService.class);
        categoryServiceFacade = new CategoryServiceFacade(categoryService);
        categoryServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var customerService = service(reqHttpClient, customerServiceUrl(apiUrl), CustomerService.class);
        customerServiceFacade = new CustomerServiceFacade(customerService);
        customerServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var deliveryNoteService = service(reqHttpClient, apiUrl, DeliveryNoteService.class);
        deliveryNoteServiceFacade = new DeliveryNoteServiceFacade(deliveryNoteService);
        deliveryNoteServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var discountGroupService = service(reqHttpClient, discountGroupServiceUrl(apiUrl), DiscountGroupService.class);
        discountGroupServiceFacade = new DiscountGroupServiceFacade(discountGroupService);
        discountGroupServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var eetSubjectService = service(reqHttpClient, eetSubjectServiceUrl(apiUrl), EetSubjectService.class);
        eetSubjectServiceFacade = new EetSubjectServiceFacade(eetSubjectService);
        eetSubjectServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var employeeService = service(reqHttpClient, employeeServiceUrl(apiUrl), EmployeeService.class);
        employeeServiceFacade = new EmployeeServiceFacade(employeeService);
        employeeServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var moneyLogService = service(reqHttpClient, moneyLogServiceUrl(apiUrl), MoneyLogService.class);
        moneyLogServiceFacade = new MoneyLogServiceFacade(moneyLogService);
        moneyLogServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var orderService = service(reqHttpClient, orderServiceUrl(apiUrl), OrderService.class);
        orderServiceFacade = new OrderServiceFacade(orderService);
        orderServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var orderItemService = service(reqHttpClient, orderItemServiceUrl(apiUrl), OrderItemService.class);
        orderItemServiceFacade = new OrderItemServiceFacade(orderItemService);
        orderItemServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var productService = service(reqHttpClient, productServiceUrl(apiUrl), ProductService.class);
        productServiceFacade = new ProductServiceFacade(productService);
        productServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var productCustomizationService = service(reqHttpClient, productCustomizationServiceUrl(apiUrl), ProductCustomizationService.class);
        productCustomizationServiceFacade = new ProductCustomizationServiceFacade(productCustomizationService);
        productCustomizationServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var productIngredientService = service(reqHttpClient, productIngredientServiceUrl(apiUrl), ProductIngredientService.class);
        productIngredientServiceFacade = new ProductIngredientServiceFacade(productIngredientService);
        productIngredientServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var reservationService = service(reqHttpClient, reservationServiceUrl(apiUrl), ReservationService.class);
        reservationServiceFacade = new ReservationServiceFacade(reservationService);
        reservationServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var supplierService = service(reqHttpClient, supplierServiceUrl(apiUrl), SupplierService.class);
        supplierServiceFacade = new SupplierServiceFacade(supplierService);
        supplierServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var tableService = service(reqHttpClient, tableServiceUrl(apiUrl), TableService.class);
        tableServiceFacade = new TableServiceFacade(tableService);
        tableServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var tagService = service(reqHttpClient, tagServiceUrl(apiUrl), TagService.class);
        tagServiceFacade = new TagServiceFacade(tagService);
        tagServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var warehouseService = service(reqHttpClient, warehouseServiceUrl(apiUrl), WarehouseService.class);
        warehouseServiceFacade = new WarehouseServiceFacade(warehouseService);
        warehouseServiceFacade.setCallExecutionListener(compositeExecutionListener);

        final var warehouseBranchService = service(reqHttpClient, warehouseBranchServiceUrl(apiUrl), WarehouseBranchService.class);
        warehouseBranchServiceFacade = new WarehouseBranchServiceFacade(warehouseBranchService);
        warehouseBranchServiceFacade.setCallExecutionListener(compositeExecutionListener);
    }

    private String discountGroupServiceUrl(String url) {
        return url + "/discount-groups";
    }

    private String eetSubjectServiceUrl(String url) {
        return url + "/eet-subjects";
    }

    private String warehouseBranchServiceUrl(String url) {
        return url + "/warehouse-branches";
    }

    private String warehouseServiceUrl(String url) {
        return url + "/warehouses";
    }

    private String tagServiceUrl(String url) {
        return url + "/tags";
    }

    private String tableServiceUrl(String url) {
        return url + "/tables";
    }

    private String supplierServiceUrl(String url) {
        return url + "/suppliers";
    }

    private String reservationServiceUrl(String url) {
        return url + "/reservations";
    }

    private String productIngredientServiceUrl(String url) {
        return url + "/product-ingredients";
    }

    private String productCustomizationServiceUrl(String url) {
        return url + "/product-customizations";
    }

    private String productServiceUrl(String url) {
        return url + "/products";
    }

    private String orderItemServiceUrl(String url) {
        return url + "/order-items";
    }

    private String orderServiceUrl(String url) {
        return url + "/orders";
    }

    private String moneyLogServiceUrl(String url) {
        return url + "/money-logs";
    }

    private String employeeServiceUrl(String url) {
        return url + "/employees";
    }

    private String categoryServiceUrl(String url) {
        return url + "/categories";
    }

    private String customerServiceUrl(String url) {
        return url + "/customers";
    }

    private String branchServiceUrl(String url) {
        return url + "/branches";
    }

    private String cloudServiceUrl(String url) {
        return url + "/clouds";
    }
}
