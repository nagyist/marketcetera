/**
 * $License$
 *
 * $Id: MDMarketstatImpl.java 16154 2012-07-14 16:34:05Z colin $
 */
package org.marketcetera.photon.model.marketdata.impl;

import java.math.BigDecimal;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.marketcetera.photon.model.marketdata.MDMarketstat;
import org.marketcetera.photon.model.marketdata.MDPackage;

import org.marketcetera.util.misc.ClassVersion;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Marketstat</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.marketcetera.photon.model.marketdata.impl.MDMarketstatImpl#getClosePrice <em>Close Price</em>}</li>
 *   <li>{@link org.marketcetera.photon.model.marketdata.impl.MDMarketstatImpl#getCloseDate <em>Close Date</em>}</li>
 *   <li>{@link org.marketcetera.photon.model.marketdata.impl.MDMarketstatImpl#getPreviousClosePrice <em>Previous Close Price</em>}</li>
 *   <li>{@link org.marketcetera.photon.model.marketdata.impl.MDMarketstatImpl#getPreviousCloseDate <em>Previous Close Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 * @since 2.0.0
 */
@ClassVersion("$Id: MDMarketstatImpl.java 16154 2012-07-14 16:34:05Z colin $")
public class MDMarketstatImpl extends MDItemImpl implements MDMarketstat {
    /**
     * The default value of the '{@link #getClosePrice() <em>Close Price</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClosePrice()
     * @generated
     * @ordered
     */
    protected static final BigDecimal CLOSE_PRICE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getClosePrice() <em>Close Price</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClosePrice()
     * @generated
     * @ordered
     */
    protected volatile BigDecimal closePrice = CLOSE_PRICE_EDEFAULT;

    /**
     * The default value of the '{@link #getCloseDate() <em>Close Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCloseDate()
     * @generated
     * @ordered
     */
    protected static final String CLOSE_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCloseDate() <em>Close Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCloseDate()
     * @generated
     * @ordered
     */
    protected volatile String closeDate = CLOSE_DATE_EDEFAULT;

    /**
     * The default value of the '{@link #getPreviousClosePrice() <em>Previous Close Price</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPreviousClosePrice()
     * @generated
     * @ordered
     */
    protected static final BigDecimal PREVIOUS_CLOSE_PRICE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreviousClosePrice() <em>Previous Close Price</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPreviousClosePrice()
     * @generated
     * @ordered
     */
    protected volatile BigDecimal previousClosePrice = PREVIOUS_CLOSE_PRICE_EDEFAULT;

    /**
     * The default value of the '{@link #getPreviousCloseDate() <em>Previous Close Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPreviousCloseDate()
     * @generated
     * @ordered
     */
    protected static final String PREVIOUS_CLOSE_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreviousCloseDate() <em>Previous Close Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPreviousCloseDate()
     * @generated
     * @ordered
     */
    protected volatile String previousCloseDate = PREVIOUS_CLOSE_DATE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MDMarketstatImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MDPackage.Literals.MD_MARKETSTAT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setClosePrice(BigDecimal newClosePrice) {
        BigDecimal oldClosePrice = closePrice;
        closePrice = newClosePrice;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    MDPackage.MD_MARKETSTAT__CLOSE_PRICE, oldClosePrice,
                    closePrice));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCloseDate() {
        return closeDate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCloseDate(String newCloseDate) {
        String oldCloseDate = closeDate;
        closeDate = newCloseDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    MDPackage.MD_MARKETSTAT__CLOSE_DATE, oldCloseDate,
                    closeDate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigDecimal getPreviousClosePrice() {
        return previousClosePrice;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPreviousClosePrice(BigDecimal newPreviousClosePrice) {
        BigDecimal oldPreviousClosePrice = previousClosePrice;
        previousClosePrice = newPreviousClosePrice;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    MDPackage.MD_MARKETSTAT__PREVIOUS_CLOSE_PRICE,
                    oldPreviousClosePrice, previousClosePrice));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPreviousCloseDate() {
        return previousCloseDate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPreviousCloseDate(String newPreviousCloseDate) {
        String oldPreviousCloseDate = previousCloseDate;
        previousCloseDate = newPreviousCloseDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    MDPackage.MD_MARKETSTAT__PREVIOUS_CLOSE_DATE,
                    oldPreviousCloseDate, previousCloseDate));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case MDPackage.MD_MARKETSTAT__CLOSE_PRICE:
            return getClosePrice();
        case MDPackage.MD_MARKETSTAT__CLOSE_DATE:
            return getCloseDate();
        case MDPackage.MD_MARKETSTAT__PREVIOUS_CLOSE_PRICE:
            return getPreviousClosePrice();
        case MDPackage.MD_MARKETSTAT__PREVIOUS_CLOSE_DATE:
            return getPreviousCloseDate();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case MDPackage.MD_MARKETSTAT__CLOSE_PRICE:
            setClosePrice((BigDecimal) newValue);
            return;
        case MDPackage.MD_MARKETSTAT__CLOSE_DATE:
            setCloseDate((String) newValue);
            return;
        case MDPackage.MD_MARKETSTAT__PREVIOUS_CLOSE_PRICE:
            setPreviousClosePrice((BigDecimal) newValue);
            return;
        case MDPackage.MD_MARKETSTAT__PREVIOUS_CLOSE_DATE:
            setPreviousCloseDate((String) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case MDPackage.MD_MARKETSTAT__CLOSE_PRICE:
            setClosePrice(CLOSE_PRICE_EDEFAULT);
            return;
        case MDPackage.MD_MARKETSTAT__CLOSE_DATE:
            setCloseDate(CLOSE_DATE_EDEFAULT);
            return;
        case MDPackage.MD_MARKETSTAT__PREVIOUS_CLOSE_PRICE:
            setPreviousClosePrice(PREVIOUS_CLOSE_PRICE_EDEFAULT);
            return;
        case MDPackage.MD_MARKETSTAT__PREVIOUS_CLOSE_DATE:
            setPreviousCloseDate(PREVIOUS_CLOSE_DATE_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case MDPackage.MD_MARKETSTAT__CLOSE_PRICE:
            return CLOSE_PRICE_EDEFAULT == null ? closePrice != null
                    : !CLOSE_PRICE_EDEFAULT.equals(closePrice);
        case MDPackage.MD_MARKETSTAT__CLOSE_DATE:
            return CLOSE_DATE_EDEFAULT == null ? closeDate != null
                    : !CLOSE_DATE_EDEFAULT.equals(closeDate);
        case MDPackage.MD_MARKETSTAT__PREVIOUS_CLOSE_PRICE:
            return PREVIOUS_CLOSE_PRICE_EDEFAULT == null ? previousClosePrice != null
                    : !PREVIOUS_CLOSE_PRICE_EDEFAULT.equals(previousClosePrice);
        case MDPackage.MD_MARKETSTAT__PREVIOUS_CLOSE_DATE:
            return PREVIOUS_CLOSE_DATE_EDEFAULT == null ? previousCloseDate != null
                    : !PREVIOUS_CLOSE_DATE_EDEFAULT.equals(previousCloseDate);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (closePrice: "); //$NON-NLS-1$
        result.append(closePrice);
        result.append(", closeDate: "); //$NON-NLS-1$
        result.append(closeDate);
        result.append(", previousClosePrice: "); //$NON-NLS-1$
        result.append(previousClosePrice);
        result.append(", previousCloseDate: "); //$NON-NLS-1$
        result.append(previousCloseDate);
        result.append(')');
        return result.toString();
    }

} //MDMarketstatImpl
