package webservice.aladin.domain.dto.sales;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * webservice.aladin.domain.dto.sales.QSalesChat is a Querydsl Projection type for SalesChat
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSalesChat extends ConstructorExpression<SalesChat> {

    private static final long serialVersionUID = -739182697L;

    public QSalesChat(com.querydsl.core.types.Expression<java.time.LocalDate> orderDate, com.querydsl.core.types.Expression<Integer> revenuePerMonth) {
        super(SalesChat.class, new Class<?>[]{java.time.LocalDate.class, int.class}, orderDate, revenuePerMonth);
    }

}

