
package com.bancodebogota.ifx.base.v1;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bancodebogota.ifx.base.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AdditionalStatus_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AdditionalStatus");
    private final static QName _DevCimBinCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimBinCount");
    private final static QName _DevCdmBinCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmBinCount");
    private final static QName _DfltDaysToPay_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DfltDaysToPay");
    private final static QName _DevDepNumOfDeposits_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevDepNumOfDeposits");
    private final static QName _StrtAcctCardsCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StrtAcctCardsCount");
    private final static QName _NumBranchOffice_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NumBranchOffice");
    private final static QName _CancellationReason_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CancellationReason");
    private final static QName _TaxYear_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TaxYear");
    private final static QName _SeqNum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SeqNum");
    private final static QName _RemainingInsts_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RemainingInsts");
    private final static QName _PassbkPagesPerBook_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PassbkPagesPerBook");
    private final static QName _PmtAuthCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtAuthCount");
    private final static QName _DaysWith_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DaysWith");
    private final static QName _PassbkNewItemsToPrint_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PassbkNewItemsToPrint");
    private final static QName _DaysToEPost_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DaysToEPost");
    private final static QName _NumInsts_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NumInsts");
    private final static QName _CompletedPmtCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CompletedPmtCount");
    private final static QName _MaxRec_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MaxRec");
    private final static QName _Years_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Years");
    private final static QName _HoldCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "HoldCount");
    private final static QName _DevCdmBinNum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmBinNum");
    private final static QName _DevCamStatusPictures_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCamStatusPictures");
    private final static QName _DevSiuVolume_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuVolume");
    private final static QName _DevCimBinNum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimBinNum");
    private final static QName _Scoring_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Scoring");
    private final static QName _EffAcctCardsCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EffAcctCardsCount");
    private final static QName _Age_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Age");
    private final static QName _ASMOrganization_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ASMOrganization");
    private final static QName _CardsQuantity_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CardsQuantity");
    private final static QName _StopCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StopCount");
    private final static QName _SentRec_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SentRec");
    private final static QName _Dependents_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Dependents");
    private final static QName _PassbkNextPage_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PassbkNextPage");
    private final static QName _DayPastDue_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DayPastDue");
    private final static QName _PassbkLinesPerPage_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PassbkLinesPerPage");
    private final static QName _PlatformXfsCmdHResult_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PlatformXfsCmdHResult");
    private final static QName _DaysCall_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DaysCall");
    private final static QName _NumEmployees_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NumEmployees");
    private final static QName _CanWind_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CanWind");
    private final static QName _Max_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Max");
    private final static QName _HistRetentionDays_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "HistRetentionDays");
    private final static QName _DevCimBinMaxCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimBinMaxCount");
    private final static QName _DevCdmBinMaxCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmBinMaxCount");
    private final static QName _DfltXferDaysToPay_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DfltXferDaysToPay");
    private final static QName _DevIdcCards_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevIdcCards");
    private final static QName _StrtAcctRelCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StrtAcctRelCount");
    private final static QName _NumRec_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NumRec");
    private final static QName _Edition_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Edition");
    private final static QName _UTCOffset_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "UTCOffset");
    private final static QName _SkipNextN_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SkipNextN");
    private final static QName _RemainingPmtCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RemainingPmtCount");
    private final static QName _OverdraftDays_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OverdraftDays");
    private final static QName _PassbkNextLine_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PassbkNextLine");
    private final static QName _DaysToPay_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DaysToPay");
    private final static QName _OriginatorType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OriginatorType");
    private final static QName _PastDuePmtCount_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PastDuePmtCount");
    private final static QName _Count_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Count");
    private final static QName _Min_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Min");
    private final static QName _BinLength_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BinLength");
    private final static QName _MatchedRec_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MatchedRec");
    private final static QName _ServerStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ServerStatusCode");
    private final static QName _Severity_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Severity");
    private final static QName _ServerStatusDesc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ServerStatusDesc");
    private final static QName _StatusDesc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StatusDesc");
    private final static QName _StatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StatusCode");
    private final static QName _TrnSeqCntr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TrnSeqCntr");
    private final static QName _PostalCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PostalCode");
    private final static QName _BankId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BankId");
    private final static QName _PhoneNum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PhoneNum");
    private final static QName _PhoneExt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PhoneExt");
    private final static QName _MsgSupt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MsgSupt");
    private final static QName _EmploymentStatus_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EmploymentStatus");
    private final static QName _IdentType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IdentType");
    private final static QName _DevSiuSafe_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuSafe");
    private final static QName _ChkOrdStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChkOrdStatusCode");
    private final static QName _DevSiuEnhancedAudio_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuEnhancedAudio");
    private final static QName _Brand_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Brand");
    private final static QName _DevChkType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevChkType");
    private final static QName _DevPtrType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevPtrType");
    private final static QName _DevCamOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCamOperation");
    private final static QName _DevCimTransport_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimTransport");
    private final static QName _DebitStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DebitStatusCode");
    private final static QName _AcctTaxType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AcctTaxType");
    private final static QName _ContactType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ContactType");
    private final static QName _DwellingType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DwellingType");
    private final static QName _Event_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Event");
    private final static QName _SpecialHandling_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SpecialHandling");
    private final static QName _PSSNetworkType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PSSNetworkType");
    private final static QName _POSEntryCapability_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "POSEntryCapability");
    private final static QName _PhoneArea_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PhoneArea");
    private final static QName _OEDCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OEDCode");
    private final static QName _MaritalStatus_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MaritalStatus");
    private final static QName _LegalStep_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LegalStep");
    private final static QName _Freq_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Freq");
    private final static QName _BillCycleType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BillCycleType");
    private final static QName _DevSiuIntTamper_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuIntTamper");
    private final static QName _CashQuality_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CashQuality");
    private final static QName _DevSiuCdmGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuCdmGuidelight");
    private final static QName _DevCdmIntermediateStacker_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmIntermediateStacker");
    private final static QName _BankSvcChkSumStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BankSvcChkSumStatusCode");
    private final static QName _DevHealthLevel_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevHealthLevel");
    private final static QName _DeliveryMethod_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DeliveryMethod");
    private final static QName _AgentType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AgentType");
    private final static QName _DevCimNoteReader_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimNoteReader");
    private final static QName _CryptType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CryptType");
    private final static QName _VoluntaryLock_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "VoluntaryLock");
    private final static QName _FileType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FileType");
    private final static QName _TaxType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TaxType");
    private final static QName _RevOptSupt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RevOptSupt");
    private final static QName _PostingTotalType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PostingTotalType");
    private final static QName _Role_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Role");
    private final static QName _PartyAcctRelType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PartyAcctRelType");
    private final static QName _MediaType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MediaType");
    private final static QName _DevTtuType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevTtuType");
    private final static QName _IdentPrevReadMethod_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IdentPrevReadMethod");
    private final static QName _DevSiuProximity_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuProximity");
    private final static QName _ChkAcceptStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChkAcceptStatusCode");
    private final static QName _DevSiuDepGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuDepGuidelight");
    private final static QName _BillType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BillType");
    private final static QName _DevCdmType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmType");
    private final static QName _DevPtrOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevPtrOperation");
    private final static QName _DevAlmType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevAlmType");
    private final static QName _DevCimShutter_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimShutter");
    private final static QName _CustType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustType");
    private final static QName _ServiceCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ServiceCode");
    private final static QName _Nationality_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Nationality");
    private final static QName _ContactInfoType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ContactInfoType");
    private final static QName _TrnType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TrnType");
    private final static QName _SettlementMethod_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SettlementMethod");
    private final static QName _PSSMsgEncryption_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PSSMsgEncryption");
    private final static QName _TrnStatusType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TrnStatusType");
    private final static QName _PmtFormat_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtFormat");
    private final static QName _NetworkOwner_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NetworkOwner");
    private final static QName _IdentVerifyResults_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IdentVerifyResults");
    private final static QName _DevSiuTamper_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuTamper");
    private final static QName _FeeType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FeeType");
    private final static QName _AssignmentSrc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AssignmentSrc");
    private final static QName _DevSiuHeating_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuHeating");
    private final static QName _CardStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CardStatusCode");
    private final static QName _DevSiuAudio_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuAudio");
    private final static QName _DevCdmBinType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmBinType");
    private final static QName _BankAcctFeatType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BankAcctFeatType");
    private final static QName _DevDepOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevDepOperation");
    private final static QName _DeliveryInstruction_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DeliveryInstruction");
    private final static QName _AddrType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AddrType");
    private final static QName _DevCimDropBox_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimDropBox");
    private final static QName _CreditStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CreditStatusCode");
    private final static QName _EmailInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EmailInd");
    private final static QName _LawyerType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LawyerType");
    private final static QName _StmtType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StmtType");
    private final static QName _ResetAuth_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ResetAuth");
    private final static QName _POSOutCapabilities_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "POSOutCapabilities");
    private final static QName _Reason_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Reason");
    private final static QName _OrgIdType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrgIdType");
    private final static QName _MediaTotalType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MediaTotalType");
    private final static QName _AdviseOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AdviseOperation");
    private final static QName _DevSiuVandalShield_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuVandalShield");
    private final static QName _GovOrg_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GovOrg");
    private final static QName _BrandChangeInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BrandChangeInd");
    private final static QName _DevSiuOperatorSwitch_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuOperatorSwitch");
    private final static QName _ChargeRegulation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChargeRegulation");
    private final static QName _DevSiuCoinInGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuCoinInGuidelight");
    private final static QName _DevCdmPositionStatus_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmPositionStatus");
    private final static QName _BillStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BillStatusCode");
    private final static QName _DevCdmTransport_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmTransport");
    private final static QName _DevPINOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevPINOperation");
    private final static QName _DependentType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DependentType");
    private final static QName _BalType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BalType");
    private final static QName _DevCimPositionStatus_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimPositionStatus");
    private final static QName _CustContactPref_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustContactPref");
    private final static QName _ChannelName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChannelName");
    private final static QName _LinkedAcct_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LinkedAcct");
    private final static QName _ClientChgCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ClientChgCode");
    private final static QName _TINType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TINType");
    private final static QName _Cosigner_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Cosigner");
    private final static QName _SecObjType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SecObjType");
    private final static QName _PrePaidBalType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PrePaidBalType");
    private final static QName _StatusMtvType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StatusMtvType");
    private final static QName _PmtAuthStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtAuthStatusCode");
    private final static QName _MsgType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MsgType");
    private final static QName _Environment_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Environment");
    private final static QName _IdentVerifyEntity_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IdentVerifyEntity");
    private final static QName _DevSiuScannerGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuScannerGuidelight");
    private final static QName _ChksumStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChksumStatusCode");
    private final static QName _DevSiuFasciaLight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuFasciaLight");
    private final static QName _CardPrefStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CardPrefStatusCode");
    private final static QName _DevCimBinType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimBinType");
    private final static QName _DevSiuAmbientLight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuAmbientLight");
    private final static QName _DevCamStatusArea_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCamStatusArea");
    private final static QName _DevCimTransportItems_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimTransportItems");
    private final static QName _DebitType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DebitType");
    private final static QName _AcctType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AcctType");
    private final static QName _ContentType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ContentType");
    private final static QName _RateInfoType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RateInfoType");
    private final static QName _ECARDInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ECARDInd");
    private final static QName _StatusModBy_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StatusModBy");
    private final static QName _PurchItemStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PurchItemStatusCode");
    private final static QName _POSLocation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "POSLocation");
    private final static QName _ProrityPassInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ProrityPassInd");
    private final static QName _OEDInstitution_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OEDInstitution");
    private final static QName _MeasureUnit_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MeasureUnit");
    private final static QName _AcctStatus_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AcctStatus");
    private final static QName _Gender_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Gender");
    private final static QName _BranchIdType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BranchIdType");
    private final static QName _DevSiuOpenClose_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuOpenClose");
    private final static QName _CashValidity_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CashValidity");
    private final static QName _DevSiuChkGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuChkGuidelight");
    private final static QName _DevCdmOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmOperation");
    private final static QName _BankSvcChkSumType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BankSvcChkSumType");
    private final static QName _DevIdcOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevIdcOperation");
    private final static QName _DepApplicantAcctRel_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DepApplicantAcctRel");
    private final static QName _Aging_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Aging");
    private final static QName _DevCimOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimOperation");
    private final static QName _CustAcctUse_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustAcctUse");
    private final static QName _BlackListType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BlackListType");
    private final static QName _Franchise_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Franchise");
    private final static QName _Technology_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Technology");
    private final static QName _RevReasonCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RevReasonCode");
    private final static QName _POSVerifyCapability_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "POSVerifyCapability");
    private final static QName _SignonType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SignonType");
    private final static QName _PersonVerifyType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PersonVerifyType");
    private final static QName _MicrReadIntegrity_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MicrReadIntegrity");
    private final static QName _DevVdmOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevVdmOperation");
    private final static QName _IdentReadMethod_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IdentReadMethod");
    private final static QName _DevSiuReceiptGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuReceiptGuidelight");
    private final static QName _ChkAcceptType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChkAcceptType");
    private final static QName _DevSiuDocumentGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuDocumentGuidelight");
    private final static QName _BiometricsStatus_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BiometricsStatus");
    private final static QName _DevChkOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevChkOperation");
    private final static QName _DevPtrSupplyLocation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevPtrSupplyLocation");
    private final static QName _DevAppOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevAppOperation");
    private final static QName _DevCimStackerItems_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimStackerItems");
    private final static QName _DebitAuthType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DebitAuthType");
    private final static QName _AcctTaxStatus_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AcctTaxStatus");
    private final static QName _ContactPref_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ContactPref");
    private final static QName _ViewDtlPref_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ViewDtlPref");
    private final static QName _CosignerInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CosignerInd");
    private final static QName _SignonRole_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SignonRole");
    private final static QName _PSSMsgMAC_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PSSMsgMAC");
    private final static QName _PmtMethod_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtMethod");
    private final static QName _NationalityType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NationalityType");
    private final static QName _NumberType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NumberType");
    private final static QName _InvoiceType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "InvoiceType");
    private final static QName _DevSiuUps_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuUps");
    private final static QName _ForeignUse_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ForeignUse");
    private final static QName _BankPreventiveLock_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BankPreventiveLock");
    private final static QName _DevSiuIdcGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuIdcGuidelight");
    private final static QName _CardType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CardType");
    private final static QName _DevSiuCabinet_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuCabinet");
    private final static QName _DevCdmCashUnits_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmCashUnits");
    private final static QName _BankIdType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BankIdType");
    private final static QName _DevDepType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevDepType");
    private final static QName _DeliveryMedia_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DeliveryMedia");
    private final static QName _AdjType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AdjType");
    private final static QName _DevCimIntermediateStacker_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimIntermediateStacker");
    private final static QName _CreditType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CreditType");
    private final static QName _VerifiedByVisa_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "VerifiedByVisa");
    private final static QName _EmailType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EmailType");
    private final static QName _SvcName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SvcName");
    private final static QName _RestartType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RestartType");
    private final static QName _PostingSessionStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PostingSessionStatusCode");
    private final static QName _RelType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RelType");
    private final static QName _OverrideType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OverrideType");
    private final static QName _MediaTrnType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MediaTrnType");
    private final static QName _DevTtuOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevTtuOperation");
    private final static QName _IdentConditions_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IdentConditions");
    private final static QName _CompanyNameInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CompanyNameInd");
    private final static QName _DevSiuPassbookGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuPassbookGuidelight");
    private final static QName _CharType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CharType");
    private final static QName _DevSiuCoinOutGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuCoinOutGuidelight");
    private final static QName _DevCdmShutter_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmShutter");
    private final static QName _BillSummAmtCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BillSummAmtCode");
    private final static QName _DevCdmTransportItems_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmTransportItems");
    private final static QName _DevPINType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevPINType");
    private final static QName _DevAlmOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevAlmOperation");
    private final static QName _DevCimSafeDoor_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimSafeDoor");
    private final static QName _CustStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustStatusCode");
    private final static QName _EanCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EanCode");
    private final static QName _MassiveProcess_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MassiveProcess");
    private final static QName _CompositeCurAmtType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CompositeCurAmtType");
    private final static QName _TrnSrc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TrnSrc");
    private final static QName _SessionType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SessionType");
    private final static QName _PSSCATSecLevel_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PSSCATSecLevel");
    private final static QName _TrademarkInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TrademarkInd");
    private final static QName _PmtEnclType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtEnclType");
    private final static QName _NameAddrType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NameAddrType");
    private final static QName _IdentVerifyMethod_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IdentVerifyMethod");
    private final static QName _DevSiuSeismic_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuSeismic");
    private final static QName _ChksumType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChksumType");
    private final static QName _ExtBalType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ExtBalType");
    private final static QName _Action_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Action");
    private final static QName _DevSiuHeat_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuHeat");
    private final static QName _CardPrefType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CardPrefType");
    private final static QName _DevSiuAudibleAlarm_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuAudibleAlarm");
    private final static QName _DevCamType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCamType");
    private final static QName _BankAcctFeatLimitType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BankAcctFeatLimitType");
    private final static QName _DevCimType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimType");
    private final static QName _DeliveryDestination_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DeliveryDestination");
    private final static QName _AckType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AckType");
    private final static QName _DevCimCashUnits_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimCashUnits");
    private final static QName _CreditAuthType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CreditAuthType");
    private final static QName _CurAmtFeeStatus_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CurAmtFeeStatus");
    private final static QName _EducationalLevel_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EducationalLevel");
    private final static QName _StmtSummType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StmtSummType");
    private final static QName _RefType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RefType");
    private final static QName _POSOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "POSOperation");
    private final static QName _PurchasePortfolioInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PurchasePortfolioInd");
    private final static QName _OptSupt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OptSupt");
    private final static QName _MediaSubType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MediaSubType");
    private final static QName _SecObjStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SecObjStatusCode");
    private final static QName _GovIssueIdentType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GovIssueIdentType");
    private final static QName _BranchPreventiveLock_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BranchPreventiveLock");
    private final static QName _DevSiuOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuOperation");
    private final static QName _CertCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CertCode");
    private final static QName _DevSiuCimGuidelight_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevSiuCimGuidelight");
    private final static QName _DevCdmPositionLocation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmPositionLocation");
    private final static QName _BillPmtStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BillPmtStatusCode");
    private final static QName _DevIdcType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevIdcType");
    private final static QName _DepBkOrdStatusCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DepBkOrdStatusCode");
    private final static QName _AuthSupt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AuthSupt");
    private final static QName _DevCimPositionLocation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimPositionLocation");
    private final static QName _CustBankPrefSupt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustBankPrefSupt");
    private final static QName _Code_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Code");
    private final static QName _IntegralLock_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IntegralLock");
    private final static QName _TerminalType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TerminalType");
    private final static QName _SecObjPurpose_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SecObjPurpose");
    private final static QName _PrcDtAdj_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PrcDtAdj");
    private final static QName _SocialStratum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SocialStratum");
    private final static QName _PhoneType_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PhoneType");
    private final static QName _CSPhoneNum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CSPhoneNum");
    private final static QName _ActivationPhone_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ActivationPhone");
    private final static QName _SafePhone_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SafePhone");
    private final static QName _Phone_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Phone");
    private final static QName _OrgPhone_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrgPhone");
    private final static QName _Fax_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Fax");
    private final static QName _EvePhone_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EvePhone");
    private final static QName _EveFax_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EveFax");
    private final static QName _DayPhone_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DayPhone");
    private final static QName _DayFax_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DayFax");
    private final static QName _OtherIdentDoc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OtherIdentDoc");
    private final static QName _Country_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Country");
    private final static QName _CityId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CityId");
    private final static QName _City_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "City");
    private final static QName _StateProv_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StateProv");
    private final static QName _TypeId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TypeId");
    private final static QName _HoldCoId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "HoldCoId");
    private final static QName _VBV_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "VBV");
    private final static QName _DepBkStyleId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DepBkStyleId");
    private final static QName _TerminalObjId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TerminalObjId");
    private final static QName _SessionId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SessionId");
    private final static QName _CSPRefId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CSPRefId");
    private final static QName _RecChkOrdId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RecChkOrdId");
    private final static QName _RemitRefId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RemitRefId");
    private final static QName _CompRemitStmtId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CompRemitStmtId");
    private final static QName _PmtId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtId");
    private final static QName _ChkBkStyleId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChkBkStyleId");
    private final static QName _Serial_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Serial");
    private final static QName _NodeIdTo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NodeIdTo");
    private final static QName _CIIU_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CIIU");
    private final static QName _ChkAcceptId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChkAcceptId");
    private final static QName _ApprovalId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ApprovalId");
    private final static QName _PartyAcctRelId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PartyAcctRelId");
    private final static QName _MediaAcctId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MediaAcctId");
    private final static QName _ForExRateId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ForExRateId");
    private final static QName _XferId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "XferId");
    private final static QName _DepBkOrdId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DepBkOrdId");
    private final static QName _TerminalId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TerminalId");
    private final static QName _ServerTerminalSeqId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ServerTerminalSeqId");
    private final static QName _CreditMediaItemId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CreditMediaItemId");
    private final static QName _PurchItemId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PurchItemId");
    private final static QName _RemitId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RemitId");
    private final static QName _CompositeCurAmtId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CompositeCurAmtId");
    private final static QName _PmtEnclId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtEnclId");
    private final static QName _RevClientTrnSeq_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RevClientTrnSeq");
    private final static QName _NodeIdFrom_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NodeIdFrom");
    private final static QName _ClientTrnSeq_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ClientTrnSeq");
    private final static QName _CardPrefId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CardPrefId");
    private final static QName _ProductNum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ProductNum");
    private final static QName _AgentId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AgentId");
    private final static QName _PmtAuthId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtAuthId");
    private final static QName _OrgIdNum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrgIdNum");
    private final static QName _MediaAcctAdjId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MediaAcctAdjId");
    private final static QName _ForExDealId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ForExDealId");
    private final static QName _ProductCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ProductCode");
    private final static QName _VoucherTypeId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "VoucherTypeId");
    private final static QName _DepAppId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DepAppId");
    private final static QName _StopChkId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StopChkId");
    private final static QName _DepAcctStmtId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DepAcctStmtId");
    private final static QName _SecSignId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SecSignId");
    private final static QName _CreditId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CreditId");
    private final static QName _PurchId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PurchId");
    private final static QName _RelatedCreditId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RelatedCreditId");
    private final static QName _ClubAcctId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ClubAcctId");
    private final static QName _PmtBatchId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtBatchId");
    private final static QName _ProductId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ProductId");
    private final static QName _NodeId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NodeId");
    private final static QName _BatchId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BatchId");
    private final static QName _CardId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CardId");
    private final static QName _AcctPayAcctId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AcctPayAcctId");
    private final static QName _PayorAcctId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PayorAcctId");
    private final static QName _Org_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Org");
    private final static QName _LockboxAcctId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LockboxAcctId");
    private final static QName _FontId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FontId");
    private final static QName _TrnAuthId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TrnAuthId");
    private final static QName _SPRefId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SPRefId");
    private final static QName _DebitId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DebitId");
    private final static QName _SecretId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SecretId");
    private final static QName _CreditAuthId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CreditAuthId");
    private final static QName _PostingSessionId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PostingSessionId");
    private final static QName _RefId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RefId");
    private final static QName _ClientTerminalSeqId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ClientTerminalSeqId");
    private final static QName _DeathCertificateId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DeathCertificateId");
    private final static QName _Identifier_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Identifier");
    private final static QName _AccountantCenter_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AccountantCenter");
    private final static QName _BillSummAmtId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BillSummAmtId");
    private final static QName _AcctPayAcct_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AcctPayAcct");
    private final static QName _PassbkLastItemPrinted_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PassbkLastItemPrinted");
    private final static QName _NewToken_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NewToken");
    private final static QName _InvItemId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "InvItemId");
    private final static QName _TrnSeq_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TrnSeq");
    private final static QName _EMVCardId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EMVCardId");
    private final static QName _FileId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FileId");
    private final static QName _Token_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Token");
    private final static QName _SPName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SPName");
    private final static QName _DebitAuthId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DebitAuthId");
    private final static QName _SecObjId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SecObjId");
    private final static QName _CreateRefId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CreateRefId");
    private final static QName _PmtRemitRefId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtRemitRefId");
    private final static QName _RecXferId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RecXferId");
    private final static QName _ChksumId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChksumId");
    private final static QName _PriorizationCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PriorizationCode");
    private final static QName _ParticipantId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ParticipantId");
    private final static QName _BillerNum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BillerNum");
    private final static QName _SubSegmentId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SubSegmentId");
    private final static QName _PhoneId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PhoneId");
    private final static QName _PassbkItemId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PassbkItemId");
    private final static QName _NetworkRefId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NetworkRefId");
    private final static QName _IdentProgramId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IdentProgramId");
    private final static QName _DevId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevId");
    private final static QName _BiometricCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BiometricCode");
    private final static QName _TerminalSPObjId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TerminalSPObjId");
    private final static QName _SessionLoginId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SessionLoginId");
    private final static QName _CustPayeeId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustPayeeId");
    private final static QName _EmailId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EmailId");
    private final static QName _RecPmtId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RecPmtId");
    private final static QName _SecEncryptId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SecEncryptId");
    private final static QName _CoverStyleId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CoverStyleId");
    private final static QName _PmtRefId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PmtRefId");
    private final static QName _ChkOrdId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChkOrdId");
    private final static QName _OTP_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OTP");
    private final static QName _ContactId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ContactId");
    private final static QName _BankSvcChkSumId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BankSvcChkSumId");
    private final static QName _SegmentId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SegmentId");
    private final static QName _PassbkId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PassbkId");
    private final static QName _MediaContainerId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MediaContainerId");
    private final static QName _StartDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StartDt");
    private final static QName _PlatformXfsTimestamp_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PlatformXfsTimestamp");
    private final static QName _ExpDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ExpDt");
    private final static QName _IssDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IssDt");
    private final static QName _CreditHeldAmtExpDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CreditHeldAmtExpDt");
    private final static QName _ClientCreateDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ClientCreateDt");
    private final static QName _SessionDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SessionDt");
    private final static QName _OrigIssueDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrigIssueDt");
    private final static QName _EndDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EndDt");
    private final static QName _ForExValDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ForExValDt");
    private final static QName _CreatedDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CreatedDt");
    private final static QName _ClientBusinessDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ClientBusinessDt");
    private final static QName _MsgDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MsgDt");
    private final static QName _ServerDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ServerDt");
    private final static QName _ExpectedAvailDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ExpectedAvailDt");
    private final static QName _NextDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NextDt");
    private final static QName _EffDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EffDt");
    private final static QName _ClientDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ClientDt");
    private final static QName _ExpectedOpenDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ExpectedOpenDt");
    private final static QName _AvailDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AvailDt");
    private final static QName _Summary_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Summary");
    private final static QName _RejectionDesc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RejectionDesc");
    private final static QName _LongCodeDesc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LongCodeDesc");
    private final static QName _TargetChnValue_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TargetChnValue");
    private final static QName _TargetChnNick_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TargetChnNick");
    private final static QName _ShortCodeDesc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ShortCodeDesc");
    private final static QName _OtherIncomeDesc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OtherIncomeDesc");
    private final static QName _EdLevel_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EdLevel");
    private final static QName _Desc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Desc");
    private final static QName _CustTypeDesc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustTypeDesc");
    private final static QName _GeneralException_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GeneralException");
    private final static QName _TrnRqUID_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TrnRqUID");
    private final static QName _RelatedItemInqRqUID_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RelatedItemInqRqUID");
    private final static QName _BillId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BillId");
    private final static QName _RqUID_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RqUID");
    private final static QName _DiscId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DiscId");
    private final static QName _SvcRqUID_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SvcRqUID");
    private final static QName _ReconRqUID_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ReconRqUID");
    private final static QName _AsyncRqUID_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AsyncRqUID");
    private final static QName _Status_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Status");
    private final static QName _AsyncRsInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AsyncRsInfo");
    private final static QName _CommercialArea_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CommercialArea");
    private final static QName _FinalPrcDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FinalPrcDt");
    private final static QName _DepMatureDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DepMatureDt");
    private final static QName _ChkClrDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChkClrDt");
    private final static QName _TrnDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TrnDt");
    private final static QName _AppExpDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AppExpDt");
    private final static QName _ResolvePendingDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ResolvePendingDt");
    private final static QName _PaidDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PaidDt");
    private final static QName _OrigDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrigDt");
    private final static QName _LastTrnDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastTrnDt");
    private final static QName _LastDepDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastDepDt");
    private final static QName _HolDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "HolDt");
    private final static QName _EstablishDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EstablishDt");
    private final static QName _DiscDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DiscDt");
    private final static QName _ClosedDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ClosedDt");
    private final static QName _BillDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BillDt");
    private final static QName _SessionPostingDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SessionPostingDt");
    private final static QName _PostedDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PostedDt");
    private final static QName _OrigPmtDueDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrigPmtDueDt");
    private final static QName _MatDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MatDt");
    private final static QName _LastYrInsPaidDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastYrInsPaidDt");
    private final static QName _LastPmtDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastPmtDt");
    private final static QName _ImportDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ImportDt");
    private final static QName _FinalDueDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FinalDueDt");
    private final static QName _DueDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DueDt");
    private final static QName _CloseDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CloseDt");
    private final static QName _BirthDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BirthDt");
    private final static QName _TaxPaidDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TaxPaidDt");
    private final static QName _AppEffDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AppEffDt");
    private final static QName _PrcDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PrcDt");
    private final static QName _OrigPmtPrcDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrigPmtPrcDt");
    private final static QName _OpenDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OpenDt");
    private final static QName _LastYrTaxPaidDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastYrTaxPaidDt");
    private final static QName _LastStmtDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastStmtDt");
    private final static QName _InsPaidDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "InsPaidDt");
    private final static QName _FACTAInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FACTAInd");
    private final static QName _GroupMemberInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GroupMemberInd");
    private final static QName _Indicator_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Indicator");
    private final static QName _IndicatorNational_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IndicatorNational");
    private final static QName _YN_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "YN");
    private final static QName _SelfTaxWithholderInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SelfTaxWithholderInd");
    private final static QName _SalesInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SalesInd");
    private final static QName _MainUsrInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MainUsrInd");
    private final static QName _GovOrgInqInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GovOrgInqInd");
    private final static QName _IncomeTaxInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncomeTaxInd");
    private final static QName _IndicatorExchange_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IndicatorExchange");
    private final static QName _LargessTaxPayerInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LargessTaxPayerInd");
    private final static QName _ScoreInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ScoreInd");
    private final static QName _AuthInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AuthInd");
    private final static QName _ProcessInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ProcessInd");
    private final static QName _PersonClient_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PersonClient");
    private final static QName _PersonName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PersonName");
    private final static QName _MiddleName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MiddleName");
    private final static QName _FirstName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FirstName");
    private final static QName _TitlePrefix_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TitlePrefix");
    private final static QName _Nickname_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Nickname");
    private final static QName _NameSuffix_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NameSuffix");
    private final static QName _LegalName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LegalName");
    private final static QName _LastName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastName");
    private final static QName _FullName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FullName");
    private final static QName _ContactInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ContactInfo");
    private final static QName _BillRetAddr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BillRetAddr");
    private final static QName _PostAddr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PostAddr");
    private final static QName _RemitAddr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RemitAddr");
    private final static QName _DeathInfoAddr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DeathInfoAddr");
    private final static QName _Addr4_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Addr4");
    private final static QName _Addr3_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Addr3");
    private final static QName _Addr2_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Addr2");
    private final static QName _Addr1_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Addr1");
    private final static QName _Coordenate_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Coordenate");
    private final static QName _ZipCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ZipCode");
    private final static QName _SafeEmailAddr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SafeEmailAddr");
    private final static QName _EmailAddr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "EmailAddr");
    private final static QName _DriversLicense_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DriversLicense");
    private final static QName _LicenseNum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LicenseNum");
    private final static QName _ResidencePlace_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ResidencePlace");
    private final static QName _FinantialInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FinantialInfo");
    private final static QName _LowCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LowCurAmt");
    private final static QName _PrepayPenalty_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PrepayPenalty");
    private final static QName _AmtY_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AmtY");
    private final static QName _LastPmtCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastPmtCurAmt");
    private final static QName _NextPmtCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NextPmtCurAmt");
    private final static QName _Outcome_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Outcome");
    private final static QName _Income_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Income");
    private final static QName _ChkCourtesyAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChkCourtesyAmt");
    private final static QName _TaxPaidCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TaxPaidCurAmt");
    private final static QName _DomXferFeeCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DomXferFeeCurAmt");
    private final static QName _MaxCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MaxCurAmt");
    private final static QName _PrevYrCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PrevYrCurAmt");
    private final static QName _LastYrInsPaidCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastYrInsPaidCurAmt");
    private final static QName _OrigCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrigCurAmt");
    private final static QName _TotalLiabilities_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TotalLiabilities");
    private final static QName _InitialCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "InitialCurAmt");
    private final static QName _IntlXferFeeCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IntlXferFeeCurAmt");
    private final static QName _TotalCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TotalCurAmt");
    private final static QName _FinalCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FinalCurAmt");
    private final static QName _MinAmtDue_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MinAmtDue");
    private final static QName _RegPmtCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RegPmtCurAmt");
    private final static QName _BalAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BalAmt");
    private final static QName _LastYrTaxPaidCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastYrTaxPaidCurAmt");
    private final static QName _PaidCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PaidCurAmt");
    private final static QName _TotalAssets_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TotalAssets");
    private final static QName _InitialDeposit_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "InitialDeposit");
    private final static QName _InvoicePremium_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "InvoicePremium");
    private final static QName _NetCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NetCurAmt");
    private final static QName _OtherAssets_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OtherAssets");
    private final static QName _Sales_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Sales");
    private final static QName _FeeAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "FeeAmt");
    private final static QName _MinCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MinCurAmt");
    private final static QName _SecAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SecAmt");
    private final static QName _ChkEnteredAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChkEnteredAmt");
    private final static QName _LOCLimit_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LOCLimit");
    private final static QName _PassbkLastBalPrinted_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PassbkLastBalPrinted");
    private final static QName _AmtX_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AmtX");
    private final static QName _InsPaidCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "InsPaidCurAmt");
    private final static QName _NetSessionTotals_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NetSessionTotals");
    private final static QName _OtherIncome_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OtherIncome");
    private final static QName _HighCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "HighCurAmt");
    private final static QName _MinPmtCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MinPmtCurAmt");
    private final static QName _CashAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CashAmt");
    private final static QName _StmtRunningBal_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "StmtRunningBal");
    private final static QName _CurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CurAmt");
    private final static QName _MillasFactor_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MillasFactor");
    private final static QName _Rate_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Rate");
    private final static QName _PlatformXfsRequestId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PlatformXfsRequestId");
    private final static QName _PlatformXfsAction_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PlatformXfsAction");
    private final static QName _BankSvcChkSumValue_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BankSvcChkSumValue");
    private final static QName _RandomNbr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RandomNbr");
    private final static QName _WelcomePoints_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "WelcomePoints");
    private final static QName _ExpRate_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ExpRate");
    private final static QName _PrePaidValue_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PrePaidValue");
    private final static QName _PlatformXfsEventId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PlatformXfsEventId");
    private final static QName _PayCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PayCurAmt");
    private final static QName _OwnerPercent_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OwnerPercent");
    private final static QName _RdmdPoints_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RdmdPoints");
    private final static QName _DevCdmBinDenomination_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCdmBinDenomination");
    private final static QName _MulRedMillas_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "MulRedMillas");
    private final static QName _PlatformXfsState_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PlatformXfsState");
    private final static QName _PlatformXfsCmdCode_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PlatformXfsCmdCode");
    private final static QName _ChksumValue_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ChksumValue");
    private final static QName _Amt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Amt");
    private final static QName _IntAPY_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IntAPY");
    private final static QName _TrnPoints_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TrnPoints");
    private final static QName _DeathCertificateInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DeathCertificateInfo");
    private final static QName _SelRangeDueDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SelRangeDueDt");
    private final static QName _SelRangePrcDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SelRangePrcDt");
    private final static QName _SelRangeDt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SelRangeDt");
    private final static QName _Memo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Memo");
    private final static QName _CompositeContactInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CompositeContactInfo");
    private final static QName _BaseEnvr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BaseEnvr");
    private final static QName _Name_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Name");
    private final static QName _SegmentName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SegmentName");
    private final static QName _IndustId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IndustId");
    private final static QName _IndustNum_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IndustNum");
    private final static QName _CustLoginId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustLoginId");
    private final static QName _LastDepCurAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "LastDepCurAmt");
    private final static QName _AuthAmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AuthAmt");
    private final static QName _CurRate_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CurRate");
    private final static QName _DevCimBinDenomination_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevCimBinDenomination");
    private final static QName _RefInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RefInfo");
    private final static QName _ModPswdFirst_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ModPswdFirst");
    private final static QName _IncToken_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncToken");
    private final static QName _IncRefInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncRefInfo");
    private final static QName _IncExtBal_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncExtBal");
    private final static QName _GetSecureInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GetSecureInfo");
    private final static QName _GetGeneralInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GetGeneralInfo");
    private final static QName _TruncatedInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "TruncatedInd");
    private final static QName _CSPCustInfoReq_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CSPCustInfoReq");
    private final static QName _BackupWithholding_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BackupWithholding");
    private final static QName _AcceptReqd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AcceptReqd");
    private final static QName _SecretOptional_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SecretOptional");
    private final static QName _RecPmtMod_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RecPmtMod");
    private final static QName _PrimaryAcct_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PrimaryAcct");
    private final static QName _POSCaptureCapability_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "POSCaptureCapability");
    private final static QName _ExternalPaymentImageAvailable_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ExternalPaymentImageAvailable");
    private final static QName _OpenAcctInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OpenAcctInd");
    private final static QName _IncCounts_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncCounts");
    private final static QName _IncAllItems_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncAllItems");
    private final static QName _GenSessKey_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GenSessKey");
    private final static QName _DevChkImageEnabled_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevChkImageEnabled");
    private final static QName _ModPending_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ModPending");
    private final static QName _NextDay_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NextDay");
    private final static QName _IncSummary_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncSummary");
    private final static QName _IncImages_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncImages");
    private final static QName _IncEndrsmntStmp_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncEndrsmntStmp");
    private final static QName _GetLocationInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GetLocationInfo");
    private final static QName _GetBasicInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GetBasicInfo");
    private final static QName _SuppressEcho_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SuppressEcho");
    private final static QName _CaseSen_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CaseSen");
    private final static QName _AllocateAllowed_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AllocateAllowed");
    private final static QName _SpaceAlwd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SpaceAlwd");
    private final static QName _SecObjInitRemKey_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SecObjInitRemKey");
    private final static QName _ProdPreAppr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ProdPreAppr");
    private final static QName _PrenoteReqd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PrenoteReqd");
    private final static QName _POSAttended_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "POSAttended");
    private final static QName _NotifyWilling_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NotifyWilling");
    private final static QName _IncBillerContact_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncBillerContact");
    private final static QName _ImmediateXfer_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ImmediateXfer");
    private final static QName _DupChkOverride_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DupChkOverride");
    private final static QName _DevAppNormalOperation_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DevAppNormalOperation");
    private final static QName _ManualMicr_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ManualMicr");
    private final static QName _IncSecSigLine_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncSecSigLine");
    private final static QName _IncHistory_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncHistory");
    private final static QName _IncDisc_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncDisc");
    private final static QName _RetryInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RetryInd");
    private final static QName _GetFinantialInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GetFinantialInfo");
    private final static QName _UpPayee_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "UpPayee");
    private final static QName _CustNameReqd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustNameReqd");
    private final static QName _CascadeDel_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CascadeDel");
    private final static QName _AccountHolder_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "AccountHolder");
    private final static QName _SettleInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SettleInd");
    private final static QName _RecXferMod_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "RecXferMod");
    private final static QName _PrintReceipt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PrintReceipt");
    private final static QName _PostAddrReqd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "PostAddrReqd");
    private final static QName _OwnerInd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OwnerInd");
    private final static QName _IncDetail_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncDetail");
    private final static QName _NotifyReqd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NotifyReqd");
    private final static QName _IncBal_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "IncBal");
    private final static QName _ImmediatePmt_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "ImmediatePmt");
    private final static QName _DiscReqd_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "DiscReqd");
    private final static QName _SvcRs_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SvcRs");
    private final static QName _UserId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "UserId");
    private final static QName _SellerId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SellerId");
    private final static QName _CustId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustId");
    private final static QName _CustPermId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "CustPermId");
    private final static QName _NetworkTrnInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "NetworkTrnInfo");
    private final static QName _BankInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BankInfo");
    private final static QName _BranchName_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BranchName");
    private final static QName _BranchId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "BranchId");
    private final static QName _SvcRq_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "SvcRq");
    private final static QName _Cut_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "Cut");
    private final static QName _OrgIdList_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrgIdList");
    private final static QName _OrgId_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrgId");
    private final static QName _OrgClient_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "OrgClient");
    private final static QName _GroupHeaderInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v1/", "GroupHeaderInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bancodebogota.ifx.base.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AdditionalStatusType }
     * 
     */
    public AdditionalStatusType createAdditionalStatusType() {
        return new AdditionalStatusType();
    }

    /**
     * Create an instance of {@link PhoneNumType }
     * 
     */
    public PhoneNumType createPhoneNumType() {
        return new PhoneNumType();
    }

    /**
     * Create an instance of {@link OtherIdentDocType }
     * 
     */
    public OtherIdentDocType createOtherIdentDocType() {
        return new OtherIdentDocType();
    }

    /**
     * Create an instance of {@link GeneralExceptionType }
     * 
     */
    public GeneralExceptionType createGeneralExceptionType() {
        return new GeneralExceptionType();
    }

    /**
     * Create an instance of {@link StatusType }
     * 
     */
    public StatusType createStatusType() {
        return new StatusType();
    }

    /**
     * Create an instance of {@link AsyncRsInfoType }
     * 
     */
    public AsyncRsInfoType createAsyncRsInfoType() {
        return new AsyncRsInfoType();
    }

    /**
     * Create an instance of {@link CommercialAreaType }
     * 
     */
    public CommercialAreaType createCommercialAreaType() {
        return new CommercialAreaType();
    }

    /**
     * Create an instance of {@link PersonClientType }
     * 
     */
    public PersonClientType createPersonClientType() {
        return new PersonClientType();
    }

    /**
     * Create an instance of {@link PersonNameType }
     * 
     */
    public PersonNameType createPersonNameType() {
        return new PersonNameType();
    }

    /**
     * Create an instance of {@link ContactInfoType }
     * 
     */
    public ContactInfoType createContactInfoType() {
        return new ContactInfoType();
    }

    /**
     * Create an instance of {@link PostAddrType }
     * 
     */
    public PostAddrType createPostAddrType() {
        return new PostAddrType();
    }

    /**
     * Create an instance of {@link DriversLicenseType }
     * 
     */
    public DriversLicenseType createDriversLicenseType() {
        return new DriversLicenseType();
    }

    /**
     * Create an instance of {@link ResidencePlaceType }
     * 
     */
    public ResidencePlaceType createResidencePlaceType() {
        return new ResidencePlaceType();
    }

    /**
     * Create an instance of {@link FinantialInfoType }
     * 
     */
    public FinantialInfoType createFinantialInfoType() {
        return new FinantialInfoType();
    }

    /**
     * Create an instance of {@link CurrencyAmountType }
     * 
     */
    public CurrencyAmountType createCurrencyAmountType() {
        return new CurrencyAmountType();
    }

    /**
     * Create an instance of {@link DeathCertificateInfoType }
     * 
     */
    public DeathCertificateInfoType createDeathCertificateInfoType() {
        return new DeathCertificateInfoType();
    }

    /**
     * Create an instance of {@link SelRangeDtType }
     * 
     */
    public SelRangeDtType createSelRangeDtType() {
        return new SelRangeDtType();
    }

    /**
     * Create an instance of {@link CompositeContactInfoType }
     * 
     */
    public CompositeContactInfoType createCompositeContactInfoType() {
        return new CompositeContactInfoType();
    }

    /**
     * Create an instance of {@link BaseEnvrType }
     * 
     */
    public BaseEnvrType createBaseEnvrType() {
        return new BaseEnvrType();
    }

    /**
     * Create an instance of {@link IndustIdType }
     * 
     */
    public IndustIdType createIndustIdType() {
        return new IndustIdType();
    }

    /**
     * Create an instance of {@link RefInfoType }
     * 
     */
    public RefInfoType createRefInfoType() {
        return new RefInfoType();
    }

    /**
     * Create an instance of {@link SvcRsType }
     * 
     */
    public SvcRsType createSvcRsType() {
        return new SvcRsType();
    }

    /**
     * Create an instance of {@link CustIdType }
     * 
     */
    public CustIdType createCustIdType() {
        return new CustIdType();
    }

    /**
     * Create an instance of {@link NetworkTrnInfoType }
     * 
     */
    public NetworkTrnInfoType createNetworkTrnInfoType() {
        return new NetworkTrnInfoType();
    }

    /**
     * Create an instance of {@link BankInfoType }
     * 
     */
    public BankInfoType createBankInfoType() {
        return new BankInfoType();
    }

    /**
     * Create an instance of {@link SvcRqType }
     * 
     */
    public SvcRqType createSvcRqType() {
        return new SvcRqType();
    }

    /**
     * Create an instance of {@link OrgIdListType }
     * 
     */
    public OrgIdListType createOrgIdListType() {
        return new OrgIdListType();
    }

    /**
     * Create an instance of {@link OrgIdType }
     * 
     */
    public OrgIdType createOrgIdType() {
        return new OrgIdType();
    }

    /**
     * Create an instance of {@link OrgClientType }
     * 
     */
    public OrgClientType createOrgClientType() {
        return new OrgClientType();
    }

    /**
     * Create an instance of {@link GroupHeaderInfoType }
     * 
     */
    public GroupHeaderInfoType createGroupHeaderInfoType() {
        return new GroupHeaderInfoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdditionalStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AdditionalStatus")
    public JAXBElement<AdditionalStatusType> createAdditionalStatus(AdditionalStatusType value) {
        return new JAXBElement<AdditionalStatusType>(_AdditionalStatus_QNAME, AdditionalStatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimBinCount")
    public JAXBElement<Long> createDevCimBinCount(Long value) {
        return new JAXBElement<Long>(_DevCimBinCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmBinCount")
    public JAXBElement<Long> createDevCdmBinCount(Long value) {
        return new JAXBElement<Long>(_DevCdmBinCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DfltDaysToPay")
    public JAXBElement<Long> createDfltDaysToPay(Long value) {
        return new JAXBElement<Long>(_DfltDaysToPay_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevDepNumOfDeposits")
    public JAXBElement<Long> createDevDepNumOfDeposits(Long value) {
        return new JAXBElement<Long>(_DevDepNumOfDeposits_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StrtAcctCardsCount")
    public JAXBElement<Long> createStrtAcctCardsCount(Long value) {
        return new JAXBElement<Long>(_StrtAcctCardsCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NumBranchOffice")
    public JAXBElement<Long> createNumBranchOffice(Long value) {
        return new JAXBElement<Long>(_NumBranchOffice_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CancellationReason")
    public JAXBElement<Long> createCancellationReason(Long value) {
        return new JAXBElement<Long>(_CancellationReason_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TaxYear")
    public JAXBElement<Long> createTaxYear(Long value) {
        return new JAXBElement<Long>(_TaxYear_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SeqNum")
    public JAXBElement<Long> createSeqNum(Long value) {
        return new JAXBElement<Long>(_SeqNum_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RemainingInsts")
    public JAXBElement<Long> createRemainingInsts(Long value) {
        return new JAXBElement<Long>(_RemainingInsts_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PassbkPagesPerBook")
    public JAXBElement<Long> createPassbkPagesPerBook(Long value) {
        return new JAXBElement<Long>(_PassbkPagesPerBook_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtAuthCount")
    public JAXBElement<Long> createPmtAuthCount(Long value) {
        return new JAXBElement<Long>(_PmtAuthCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DaysWith")
    public JAXBElement<Long> createDaysWith(Long value) {
        return new JAXBElement<Long>(_DaysWith_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PassbkNewItemsToPrint")
    public JAXBElement<Long> createPassbkNewItemsToPrint(Long value) {
        return new JAXBElement<Long>(_PassbkNewItemsToPrint_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DaysToEPost")
    public JAXBElement<Long> createDaysToEPost(Long value) {
        return new JAXBElement<Long>(_DaysToEPost_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NumInsts")
    public JAXBElement<Long> createNumInsts(Long value) {
        return new JAXBElement<Long>(_NumInsts_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CompletedPmtCount")
    public JAXBElement<Long> createCompletedPmtCount(Long value) {
        return new JAXBElement<Long>(_CompletedPmtCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MaxRec")
    public JAXBElement<Long> createMaxRec(Long value) {
        return new JAXBElement<Long>(_MaxRec_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Years")
    public JAXBElement<Long> createYears(Long value) {
        return new JAXBElement<Long>(_Years_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "HoldCount")
    public JAXBElement<Long> createHoldCount(Long value) {
        return new JAXBElement<Long>(_HoldCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmBinNum")
    public JAXBElement<Long> createDevCdmBinNum(Long value) {
        return new JAXBElement<Long>(_DevCdmBinNum_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCamStatusPictures")
    public JAXBElement<Long> createDevCamStatusPictures(Long value) {
        return new JAXBElement<Long>(_DevCamStatusPictures_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuVolume")
    public JAXBElement<Long> createDevSiuVolume(Long value) {
        return new JAXBElement<Long>(_DevSiuVolume_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimBinNum")
    public JAXBElement<Long> createDevCimBinNum(Long value) {
        return new JAXBElement<Long>(_DevCimBinNum_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Scoring")
    public JAXBElement<Long> createScoring(Long value) {
        return new JAXBElement<Long>(_Scoring_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EffAcctCardsCount")
    public JAXBElement<Long> createEffAcctCardsCount(Long value) {
        return new JAXBElement<Long>(_EffAcctCardsCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Age")
    public JAXBElement<Long> createAge(Long value) {
        return new JAXBElement<Long>(_Age_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ASMOrganization")
    public JAXBElement<Long> createASMOrganization(Long value) {
        return new JAXBElement<Long>(_ASMOrganization_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CardsQuantity")
    public JAXBElement<Long> createCardsQuantity(Long value) {
        return new JAXBElement<Long>(_CardsQuantity_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StopCount")
    public JAXBElement<Long> createStopCount(Long value) {
        return new JAXBElement<Long>(_StopCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SentRec")
    public JAXBElement<Long> createSentRec(Long value) {
        return new JAXBElement<Long>(_SentRec_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Dependents")
    public JAXBElement<Long> createDependents(Long value) {
        return new JAXBElement<Long>(_Dependents_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PassbkNextPage")
    public JAXBElement<Long> createPassbkNextPage(Long value) {
        return new JAXBElement<Long>(_PassbkNextPage_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DayPastDue")
    public JAXBElement<Long> createDayPastDue(Long value) {
        return new JAXBElement<Long>(_DayPastDue_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PassbkLinesPerPage")
    public JAXBElement<Long> createPassbkLinesPerPage(Long value) {
        return new JAXBElement<Long>(_PassbkLinesPerPage_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PlatformXfsCmdHResult")
    public JAXBElement<Long> createPlatformXfsCmdHResult(Long value) {
        return new JAXBElement<Long>(_PlatformXfsCmdHResult_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DaysCall")
    public JAXBElement<Long> createDaysCall(Long value) {
        return new JAXBElement<Long>(_DaysCall_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NumEmployees")
    public JAXBElement<Long> createNumEmployees(Long value) {
        return new JAXBElement<Long>(_NumEmployees_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CanWind")
    public JAXBElement<Long> createCanWind(Long value) {
        return new JAXBElement<Long>(_CanWind_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Max")
    public JAXBElement<Long> createMax(Long value) {
        return new JAXBElement<Long>(_Max_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "HistRetentionDays")
    public JAXBElement<Long> createHistRetentionDays(Long value) {
        return new JAXBElement<Long>(_HistRetentionDays_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimBinMaxCount")
    public JAXBElement<Long> createDevCimBinMaxCount(Long value) {
        return new JAXBElement<Long>(_DevCimBinMaxCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmBinMaxCount")
    public JAXBElement<Long> createDevCdmBinMaxCount(Long value) {
        return new JAXBElement<Long>(_DevCdmBinMaxCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DfltXferDaysToPay")
    public JAXBElement<Long> createDfltXferDaysToPay(Long value) {
        return new JAXBElement<Long>(_DfltXferDaysToPay_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevIdcCards")
    public JAXBElement<Long> createDevIdcCards(Long value) {
        return new JAXBElement<Long>(_DevIdcCards_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StrtAcctRelCount")
    public JAXBElement<Long> createStrtAcctRelCount(Long value) {
        return new JAXBElement<Long>(_StrtAcctRelCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NumRec")
    public JAXBElement<Long> createNumRec(Long value) {
        return new JAXBElement<Long>(_NumRec_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Edition")
    public JAXBElement<Long> createEdition(Long value) {
        return new JAXBElement<Long>(_Edition_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "UTCOffset")
    public JAXBElement<Long> createUTCOffset(Long value) {
        return new JAXBElement<Long>(_UTCOffset_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SkipNextN")
    public JAXBElement<Long> createSkipNextN(Long value) {
        return new JAXBElement<Long>(_SkipNextN_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RemainingPmtCount")
    public JAXBElement<Long> createRemainingPmtCount(Long value) {
        return new JAXBElement<Long>(_RemainingPmtCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OverdraftDays")
    public JAXBElement<Long> createOverdraftDays(Long value) {
        return new JAXBElement<Long>(_OverdraftDays_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PassbkNextLine")
    public JAXBElement<Long> createPassbkNextLine(Long value) {
        return new JAXBElement<Long>(_PassbkNextLine_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DaysToPay")
    public JAXBElement<Long> createDaysToPay(Long value) {
        return new JAXBElement<Long>(_DaysToPay_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OriginatorType")
    public JAXBElement<Long> createOriginatorType(Long value) {
        return new JAXBElement<Long>(_OriginatorType_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PastDuePmtCount")
    public JAXBElement<Long> createPastDuePmtCount(Long value) {
        return new JAXBElement<Long>(_PastDuePmtCount_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Count")
    public JAXBElement<Long> createCount(Long value) {
        return new JAXBElement<Long>(_Count_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Min")
    public JAXBElement<Long> createMin(Long value) {
        return new JAXBElement<Long>(_Min_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BinLength")
    public JAXBElement<Long> createBinLength(Long value) {
        return new JAXBElement<Long>(_BinLength_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MatchedRec")
    public JAXBElement<Long> createMatchedRec(Long value) {
        return new JAXBElement<Long>(_MatchedRec_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ServerStatusCode")
    public JAXBElement<String> createServerStatusCode(String value) {
        return new JAXBElement<String>(_ServerStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeverityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Severity")
    public JAXBElement<SeverityType> createSeverity(SeverityType value) {
        return new JAXBElement<SeverityType>(_Severity_QNAME, SeverityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ServerStatusDesc")
    public JAXBElement<String> createServerStatusDesc(String value) {
        return new JAXBElement<String>(_ServerStatusDesc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StatusDesc")
    public JAXBElement<String> createStatusDesc(String value) {
        return new JAXBElement<String>(_StatusDesc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StatusCode")
    public JAXBElement<String> createStatusCode(String value) {
        return new JAXBElement<String>(_StatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TrnSeqCntr")
    public JAXBElement<String> createTrnSeqCntr(String value) {
        return new JAXBElement<String>(_TrnSeqCntr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PostalCode")
    public JAXBElement<String> createPostalCode(String value) {
        return new JAXBElement<String>(_PostalCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BankId")
    public JAXBElement<String> createBankId(String value) {
        return new JAXBElement<String>(_BankId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PhoneNum")
    public JAXBElement<PhoneNumType> createPhoneNum(PhoneNumType value) {
        return new JAXBElement<PhoneNumType>(_PhoneNum_QNAME, PhoneNumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PhoneExt")
    public JAXBElement<String> createPhoneExt(String value) {
        return new JAXBElement<String>(_PhoneExt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MsgSupt")
    public JAXBElement<String> createMsgSupt(String value) {
        return new JAXBElement<String>(_MsgSupt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EmploymentStatus")
    public JAXBElement<String> createEmploymentStatus(String value) {
        return new JAXBElement<String>(_EmploymentStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IdentType")
    public JAXBElement<String> createIdentType(String value) {
        return new JAXBElement<String>(_IdentType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuSafe")
    public JAXBElement<String> createDevSiuSafe(String value) {
        return new JAXBElement<String>(_DevSiuSafe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChkOrdStatusCode")
    public JAXBElement<String> createChkOrdStatusCode(String value) {
        return new JAXBElement<String>(_ChkOrdStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuEnhancedAudio")
    public JAXBElement<String> createDevSiuEnhancedAudio(String value) {
        return new JAXBElement<String>(_DevSiuEnhancedAudio_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Brand")
    public JAXBElement<String> createBrand(String value) {
        return new JAXBElement<String>(_Brand_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevChkType")
    public JAXBElement<String> createDevChkType(String value) {
        return new JAXBElement<String>(_DevChkType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevPtrType")
    public JAXBElement<String> createDevPtrType(String value) {
        return new JAXBElement<String>(_DevPtrType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCamOperation")
    public JAXBElement<String> createDevCamOperation(String value) {
        return new JAXBElement<String>(_DevCamOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimTransport")
    public JAXBElement<String> createDevCimTransport(String value) {
        return new JAXBElement<String>(_DevCimTransport_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DebitStatusCode")
    public JAXBElement<String> createDebitStatusCode(String value) {
        return new JAXBElement<String>(_DebitStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AcctTaxType")
    public JAXBElement<String> createAcctTaxType(String value) {
        return new JAXBElement<String>(_AcctTaxType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ContactType")
    public JAXBElement<String> createContactType(String value) {
        return new JAXBElement<String>(_ContactType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DwellingType")
    public JAXBElement<String> createDwellingType(String value) {
        return new JAXBElement<String>(_DwellingType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Event")
    public JAXBElement<String> createEvent(String value) {
        return new JAXBElement<String>(_Event_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SpecialHandling")
    public JAXBElement<String> createSpecialHandling(String value) {
        return new JAXBElement<String>(_SpecialHandling_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PSSNetworkType")
    public JAXBElement<String> createPSSNetworkType(String value) {
        return new JAXBElement<String>(_PSSNetworkType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "POSEntryCapability")
    public JAXBElement<String> createPOSEntryCapability(String value) {
        return new JAXBElement<String>(_POSEntryCapability_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PhoneArea")
    public JAXBElement<String> createPhoneArea(String value) {
        return new JAXBElement<String>(_PhoneArea_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OEDCode")
    public JAXBElement<String> createOEDCode(String value) {
        return new JAXBElement<String>(_OEDCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MaritalStatus")
    public JAXBElement<String> createMaritalStatus(String value) {
        return new JAXBElement<String>(_MaritalStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LegalStep")
    public JAXBElement<String> createLegalStep(String value) {
        return new JAXBElement<String>(_LegalStep_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Freq")
    public JAXBElement<String> createFreq(String value) {
        return new JAXBElement<String>(_Freq_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BillCycleType")
    public JAXBElement<String> createBillCycleType(String value) {
        return new JAXBElement<String>(_BillCycleType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuIntTamper")
    public JAXBElement<String> createDevSiuIntTamper(String value) {
        return new JAXBElement<String>(_DevSiuIntTamper_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CashQuality")
    public JAXBElement<String> createCashQuality(String value) {
        return new JAXBElement<String>(_CashQuality_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuCdmGuidelight")
    public JAXBElement<String> createDevSiuCdmGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuCdmGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmIntermediateStacker")
    public JAXBElement<String> createDevCdmIntermediateStacker(String value) {
        return new JAXBElement<String>(_DevCdmIntermediateStacker_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BankSvcChkSumStatusCode")
    public JAXBElement<String> createBankSvcChkSumStatusCode(String value) {
        return new JAXBElement<String>(_BankSvcChkSumStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevHealthLevel")
    public JAXBElement<String> createDevHealthLevel(String value) {
        return new JAXBElement<String>(_DevHealthLevel_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DeliveryMethod")
    public JAXBElement<String> createDeliveryMethod(String value) {
        return new JAXBElement<String>(_DeliveryMethod_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AgentType")
    public JAXBElement<String> createAgentType(String value) {
        return new JAXBElement<String>(_AgentType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimNoteReader")
    public JAXBElement<String> createDevCimNoteReader(String value) {
        return new JAXBElement<String>(_DevCimNoteReader_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CryptType")
    public JAXBElement<String> createCryptType(String value) {
        return new JAXBElement<String>(_CryptType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "VoluntaryLock")
    public JAXBElement<String> createVoluntaryLock(String value) {
        return new JAXBElement<String>(_VoluntaryLock_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FileType")
    public JAXBElement<String> createFileType(String value) {
        return new JAXBElement<String>(_FileType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TaxType")
    public JAXBElement<String> createTaxType(String value) {
        return new JAXBElement<String>(_TaxType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RevOptSupt")
    public JAXBElement<String> createRevOptSupt(String value) {
        return new JAXBElement<String>(_RevOptSupt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PostingTotalType")
    public JAXBElement<String> createPostingTotalType(String value) {
        return new JAXBElement<String>(_PostingTotalType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Role")
    public JAXBElement<String> createRole(String value) {
        return new JAXBElement<String>(_Role_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PartyAcctRelType")
    public JAXBElement<String> createPartyAcctRelType(String value) {
        return new JAXBElement<String>(_PartyAcctRelType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MediaType")
    public JAXBElement<String> createMediaType(String value) {
        return new JAXBElement<String>(_MediaType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevTtuType")
    public JAXBElement<String> createDevTtuType(String value) {
        return new JAXBElement<String>(_DevTtuType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IdentPrevReadMethod")
    public JAXBElement<String> createIdentPrevReadMethod(String value) {
        return new JAXBElement<String>(_IdentPrevReadMethod_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuProximity")
    public JAXBElement<String> createDevSiuProximity(String value) {
        return new JAXBElement<String>(_DevSiuProximity_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChkAcceptStatusCode")
    public JAXBElement<String> createChkAcceptStatusCode(String value) {
        return new JAXBElement<String>(_ChkAcceptStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuDepGuidelight")
    public JAXBElement<String> createDevSiuDepGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuDepGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BillType")
    public JAXBElement<String> createBillType(String value) {
        return new JAXBElement<String>(_BillType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmType")
    public JAXBElement<String> createDevCdmType(String value) {
        return new JAXBElement<String>(_DevCdmType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevPtrOperation")
    public JAXBElement<String> createDevPtrOperation(String value) {
        return new JAXBElement<String>(_DevPtrOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevAlmType")
    public JAXBElement<String> createDevAlmType(String value) {
        return new JAXBElement<String>(_DevAlmType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimShutter")
    public JAXBElement<String> createDevCimShutter(String value) {
        return new JAXBElement<String>(_DevCimShutter_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustType")
    public JAXBElement<String> createCustType(String value) {
        return new JAXBElement<String>(_CustType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ServiceCode")
    public JAXBElement<String> createServiceCode(String value) {
        return new JAXBElement<String>(_ServiceCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Nationality")
    public JAXBElement<String> createNationality(String value) {
        return new JAXBElement<String>(_Nationality_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ContactInfoType")
    public JAXBElement<String> createContactInfoType(String value) {
        return new JAXBElement<String>(_ContactInfoType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TrnType")
    public JAXBElement<String> createTrnType(String value) {
        return new JAXBElement<String>(_TrnType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SettlementMethod")
    public JAXBElement<String> createSettlementMethod(String value) {
        return new JAXBElement<String>(_SettlementMethod_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PSSMsgEncryption")
    public JAXBElement<String> createPSSMsgEncryption(String value) {
        return new JAXBElement<String>(_PSSMsgEncryption_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TrnStatusType")
    public JAXBElement<String> createTrnStatusType(String value) {
        return new JAXBElement<String>(_TrnStatusType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtFormat")
    public JAXBElement<String> createPmtFormat(String value) {
        return new JAXBElement<String>(_PmtFormat_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NetworkOwner")
    public JAXBElement<String> createNetworkOwner(String value) {
        return new JAXBElement<String>(_NetworkOwner_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IdentVerifyResults")
    public JAXBElement<String> createIdentVerifyResults(String value) {
        return new JAXBElement<String>(_IdentVerifyResults_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuTamper")
    public JAXBElement<String> createDevSiuTamper(String value) {
        return new JAXBElement<String>(_DevSiuTamper_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FeeType")
    public JAXBElement<String> createFeeType(String value) {
        return new JAXBElement<String>(_FeeType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AssignmentSrc")
    public JAXBElement<String> createAssignmentSrc(String value) {
        return new JAXBElement<String>(_AssignmentSrc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuHeating")
    public JAXBElement<String> createDevSiuHeating(String value) {
        return new JAXBElement<String>(_DevSiuHeating_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CardStatusCode")
    public JAXBElement<String> createCardStatusCode(String value) {
        return new JAXBElement<String>(_CardStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuAudio")
    public JAXBElement<String> createDevSiuAudio(String value) {
        return new JAXBElement<String>(_DevSiuAudio_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmBinType")
    public JAXBElement<String> createDevCdmBinType(String value) {
        return new JAXBElement<String>(_DevCdmBinType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BankAcctFeatType")
    public JAXBElement<String> createBankAcctFeatType(String value) {
        return new JAXBElement<String>(_BankAcctFeatType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevDepOperation")
    public JAXBElement<String> createDevDepOperation(String value) {
        return new JAXBElement<String>(_DevDepOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DeliveryInstruction")
    public JAXBElement<String> createDeliveryInstruction(String value) {
        return new JAXBElement<String>(_DeliveryInstruction_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AddrType")
    public JAXBElement<String> createAddrType(String value) {
        return new JAXBElement<String>(_AddrType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimDropBox")
    public JAXBElement<String> createDevCimDropBox(String value) {
        return new JAXBElement<String>(_DevCimDropBox_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CreditStatusCode")
    public JAXBElement<String> createCreditStatusCode(String value) {
        return new JAXBElement<String>(_CreditStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EmailInd")
    public JAXBElement<String> createEmailInd(String value) {
        return new JAXBElement<String>(_EmailInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LawyerType")
    public JAXBElement<String> createLawyerType(String value) {
        return new JAXBElement<String>(_LawyerType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StmtType")
    public JAXBElement<String> createStmtType(String value) {
        return new JAXBElement<String>(_StmtType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ResetAuth")
    public JAXBElement<String> createResetAuth(String value) {
        return new JAXBElement<String>(_ResetAuth_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "POSOutCapabilities")
    public JAXBElement<String> createPOSOutCapabilities(String value) {
        return new JAXBElement<String>(_POSOutCapabilities_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Reason")
    public JAXBElement<String> createReason(String value) {
        return new JAXBElement<String>(_Reason_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrgIdType")
    public JAXBElement<String> createOrgIdType(String value) {
        return new JAXBElement<String>(_OrgIdType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MediaTotalType")
    public JAXBElement<String> createMediaTotalType(String value) {
        return new JAXBElement<String>(_MediaTotalType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AdviseOperation")
    public JAXBElement<String> createAdviseOperation(String value) {
        return new JAXBElement<String>(_AdviseOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuVandalShield")
    public JAXBElement<String> createDevSiuVandalShield(String value) {
        return new JAXBElement<String>(_DevSiuVandalShield_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GovOrg")
    public JAXBElement<String> createGovOrg(String value) {
        return new JAXBElement<String>(_GovOrg_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BrandChangeInd")
    public JAXBElement<String> createBrandChangeInd(String value) {
        return new JAXBElement<String>(_BrandChangeInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuOperatorSwitch")
    public JAXBElement<String> createDevSiuOperatorSwitch(String value) {
        return new JAXBElement<String>(_DevSiuOperatorSwitch_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChargeRegulation")
    public JAXBElement<String> createChargeRegulation(String value) {
        return new JAXBElement<String>(_ChargeRegulation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuCoinInGuidelight")
    public JAXBElement<String> createDevSiuCoinInGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuCoinInGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmPositionStatus")
    public JAXBElement<String> createDevCdmPositionStatus(String value) {
        return new JAXBElement<String>(_DevCdmPositionStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BillStatusCode")
    public JAXBElement<String> createBillStatusCode(String value) {
        return new JAXBElement<String>(_BillStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmTransport")
    public JAXBElement<String> createDevCdmTransport(String value) {
        return new JAXBElement<String>(_DevCdmTransport_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevPINOperation")
    public JAXBElement<String> createDevPINOperation(String value) {
        return new JAXBElement<String>(_DevPINOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DependentType")
    public JAXBElement<String> createDependentType(String value) {
        return new JAXBElement<String>(_DependentType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BalType")
    public JAXBElement<String> createBalType(String value) {
        return new JAXBElement<String>(_BalType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimPositionStatus")
    public JAXBElement<String> createDevCimPositionStatus(String value) {
        return new JAXBElement<String>(_DevCimPositionStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustContactPref")
    public JAXBElement<String> createCustContactPref(String value) {
        return new JAXBElement<String>(_CustContactPref_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChannelName")
    public JAXBElement<String> createChannelName(String value) {
        return new JAXBElement<String>(_ChannelName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LinkedAcct")
    public JAXBElement<String> createLinkedAcct(String value) {
        return new JAXBElement<String>(_LinkedAcct_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ClientChgCode")
    public JAXBElement<String> createClientChgCode(String value) {
        return new JAXBElement<String>(_ClientChgCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TINType")
    public JAXBElement<String> createTINType(String value) {
        return new JAXBElement<String>(_TINType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Cosigner")
    public JAXBElement<String> createCosigner(String value) {
        return new JAXBElement<String>(_Cosigner_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SecObjType")
    public JAXBElement<String> createSecObjType(String value) {
        return new JAXBElement<String>(_SecObjType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PrePaidBalType")
    public JAXBElement<String> createPrePaidBalType(String value) {
        return new JAXBElement<String>(_PrePaidBalType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StatusMtvType")
    public JAXBElement<String> createStatusMtvType(String value) {
        return new JAXBElement<String>(_StatusMtvType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtAuthStatusCode")
    public JAXBElement<String> createPmtAuthStatusCode(String value) {
        return new JAXBElement<String>(_PmtAuthStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MsgType")
    public JAXBElement<String> createMsgType(String value) {
        return new JAXBElement<String>(_MsgType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Environment")
    public JAXBElement<String> createEnvironment(String value) {
        return new JAXBElement<String>(_Environment_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IdentVerifyEntity")
    public JAXBElement<String> createIdentVerifyEntity(String value) {
        return new JAXBElement<String>(_IdentVerifyEntity_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuScannerGuidelight")
    public JAXBElement<String> createDevSiuScannerGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuScannerGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChksumStatusCode")
    public JAXBElement<String> createChksumStatusCode(String value) {
        return new JAXBElement<String>(_ChksumStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuFasciaLight")
    public JAXBElement<String> createDevSiuFasciaLight(String value) {
        return new JAXBElement<String>(_DevSiuFasciaLight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CardPrefStatusCode")
    public JAXBElement<String> createCardPrefStatusCode(String value) {
        return new JAXBElement<String>(_CardPrefStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimBinType")
    public JAXBElement<String> createDevCimBinType(String value) {
        return new JAXBElement<String>(_DevCimBinType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuAmbientLight")
    public JAXBElement<String> createDevSiuAmbientLight(String value) {
        return new JAXBElement<String>(_DevSiuAmbientLight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCamStatusArea")
    public JAXBElement<String> createDevCamStatusArea(String value) {
        return new JAXBElement<String>(_DevCamStatusArea_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimTransportItems")
    public JAXBElement<String> createDevCimTransportItems(String value) {
        return new JAXBElement<String>(_DevCimTransportItems_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DebitType")
    public JAXBElement<String> createDebitType(String value) {
        return new JAXBElement<String>(_DebitType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AcctType")
    public JAXBElement<String> createAcctType(String value) {
        return new JAXBElement<String>(_AcctType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ContentType")
    public JAXBElement<String> createContentType(String value) {
        return new JAXBElement<String>(_ContentType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RateInfoType")
    public JAXBElement<String> createRateInfoType(String value) {
        return new JAXBElement<String>(_RateInfoType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ECARDInd")
    public JAXBElement<String> createECARDInd(String value) {
        return new JAXBElement<String>(_ECARDInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StatusModBy")
    public JAXBElement<String> createStatusModBy(String value) {
        return new JAXBElement<String>(_StatusModBy_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PurchItemStatusCode")
    public JAXBElement<String> createPurchItemStatusCode(String value) {
        return new JAXBElement<String>(_PurchItemStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "POSLocation")
    public JAXBElement<String> createPOSLocation(String value) {
        return new JAXBElement<String>(_POSLocation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ProrityPassInd")
    public JAXBElement<String> createProrityPassInd(String value) {
        return new JAXBElement<String>(_ProrityPassInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OEDInstitution")
    public JAXBElement<String> createOEDInstitution(String value) {
        return new JAXBElement<String>(_OEDInstitution_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MeasureUnit")
    public JAXBElement<String> createMeasureUnit(String value) {
        return new JAXBElement<String>(_MeasureUnit_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AcctStatus")
    public JAXBElement<String> createAcctStatus(String value) {
        return new JAXBElement<String>(_AcctStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Gender")
    public JAXBElement<String> createGender(String value) {
        return new JAXBElement<String>(_Gender_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BranchIdType")
    public JAXBElement<String> createBranchIdType(String value) {
        return new JAXBElement<String>(_BranchIdType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuOpenClose")
    public JAXBElement<String> createDevSiuOpenClose(String value) {
        return new JAXBElement<String>(_DevSiuOpenClose_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CashValidity")
    public JAXBElement<String> createCashValidity(String value) {
        return new JAXBElement<String>(_CashValidity_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuChkGuidelight")
    public JAXBElement<String> createDevSiuChkGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuChkGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmOperation")
    public JAXBElement<String> createDevCdmOperation(String value) {
        return new JAXBElement<String>(_DevCdmOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BankSvcChkSumType")
    public JAXBElement<String> createBankSvcChkSumType(String value) {
        return new JAXBElement<String>(_BankSvcChkSumType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevIdcOperation")
    public JAXBElement<String> createDevIdcOperation(String value) {
        return new JAXBElement<String>(_DevIdcOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DepApplicantAcctRel")
    public JAXBElement<String> createDepApplicantAcctRel(String value) {
        return new JAXBElement<String>(_DepApplicantAcctRel_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Aging")
    public JAXBElement<String> createAging(String value) {
        return new JAXBElement<String>(_Aging_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimOperation")
    public JAXBElement<String> createDevCimOperation(String value) {
        return new JAXBElement<String>(_DevCimOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustAcctUse")
    public JAXBElement<String> createCustAcctUse(String value) {
        return new JAXBElement<String>(_CustAcctUse_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BlackListType")
    public JAXBElement<String> createBlackListType(String value) {
        return new JAXBElement<String>(_BlackListType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Franchise")
    public JAXBElement<String> createFranchise(String value) {
        return new JAXBElement<String>(_Franchise_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Technology")
    public JAXBElement<String> createTechnology(String value) {
        return new JAXBElement<String>(_Technology_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RevReasonCode")
    public JAXBElement<String> createRevReasonCode(String value) {
        return new JAXBElement<String>(_RevReasonCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "POSVerifyCapability")
    public JAXBElement<String> createPOSVerifyCapability(String value) {
        return new JAXBElement<String>(_POSVerifyCapability_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SignonType")
    public JAXBElement<String> createSignonType(String value) {
        return new JAXBElement<String>(_SignonType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PersonVerifyType")
    public JAXBElement<String> createPersonVerifyType(String value) {
        return new JAXBElement<String>(_PersonVerifyType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MicrReadIntegrity")
    public JAXBElement<String> createMicrReadIntegrity(String value) {
        return new JAXBElement<String>(_MicrReadIntegrity_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevVdmOperation")
    public JAXBElement<String> createDevVdmOperation(String value) {
        return new JAXBElement<String>(_DevVdmOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IdentReadMethod")
    public JAXBElement<String> createIdentReadMethod(String value) {
        return new JAXBElement<String>(_IdentReadMethod_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuReceiptGuidelight")
    public JAXBElement<String> createDevSiuReceiptGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuReceiptGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChkAcceptType")
    public JAXBElement<String> createChkAcceptType(String value) {
        return new JAXBElement<String>(_ChkAcceptType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuDocumentGuidelight")
    public JAXBElement<String> createDevSiuDocumentGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuDocumentGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BiometricsStatus")
    public JAXBElement<String> createBiometricsStatus(String value) {
        return new JAXBElement<String>(_BiometricsStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevChkOperation")
    public JAXBElement<String> createDevChkOperation(String value) {
        return new JAXBElement<String>(_DevChkOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevPtrSupplyLocation")
    public JAXBElement<String> createDevPtrSupplyLocation(String value) {
        return new JAXBElement<String>(_DevPtrSupplyLocation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevAppOperation")
    public JAXBElement<String> createDevAppOperation(String value) {
        return new JAXBElement<String>(_DevAppOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimStackerItems")
    public JAXBElement<String> createDevCimStackerItems(String value) {
        return new JAXBElement<String>(_DevCimStackerItems_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DebitAuthType")
    public JAXBElement<String> createDebitAuthType(String value) {
        return new JAXBElement<String>(_DebitAuthType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AcctTaxStatus")
    public JAXBElement<String> createAcctTaxStatus(String value) {
        return new JAXBElement<String>(_AcctTaxStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ContactPref")
    public JAXBElement<String> createContactPref(String value) {
        return new JAXBElement<String>(_ContactPref_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ViewDtlPref")
    public JAXBElement<String> createViewDtlPref(String value) {
        return new JAXBElement<String>(_ViewDtlPref_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CosignerInd")
    public JAXBElement<String> createCosignerInd(String value) {
        return new JAXBElement<String>(_CosignerInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SignonRole")
    public JAXBElement<String> createSignonRole(String value) {
        return new JAXBElement<String>(_SignonRole_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PSSMsgMAC")
    public JAXBElement<String> createPSSMsgMAC(String value) {
        return new JAXBElement<String>(_PSSMsgMAC_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtMethod")
    public JAXBElement<String> createPmtMethod(String value) {
        return new JAXBElement<String>(_PmtMethod_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NationalityType")
    public JAXBElement<String> createNationalityType(String value) {
        return new JAXBElement<String>(_NationalityType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NumberType")
    public JAXBElement<String> createNumberType(String value) {
        return new JAXBElement<String>(_NumberType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "InvoiceType")
    public JAXBElement<String> createInvoiceType(String value) {
        return new JAXBElement<String>(_InvoiceType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuUps")
    public JAXBElement<String> createDevSiuUps(String value) {
        return new JAXBElement<String>(_DevSiuUps_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ForeignUse")
    public JAXBElement<String> createForeignUse(String value) {
        return new JAXBElement<String>(_ForeignUse_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BankPreventiveLock")
    public JAXBElement<String> createBankPreventiveLock(String value) {
        return new JAXBElement<String>(_BankPreventiveLock_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuIdcGuidelight")
    public JAXBElement<String> createDevSiuIdcGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuIdcGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CardType")
    public JAXBElement<String> createCardType(String value) {
        return new JAXBElement<String>(_CardType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuCabinet")
    public JAXBElement<String> createDevSiuCabinet(String value) {
        return new JAXBElement<String>(_DevSiuCabinet_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmCashUnits")
    public JAXBElement<String> createDevCdmCashUnits(String value) {
        return new JAXBElement<String>(_DevCdmCashUnits_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BankIdType")
    public JAXBElement<String> createBankIdType(String value) {
        return new JAXBElement<String>(_BankIdType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevDepType")
    public JAXBElement<String> createDevDepType(String value) {
        return new JAXBElement<String>(_DevDepType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DeliveryMedia")
    public JAXBElement<String> createDeliveryMedia(String value) {
        return new JAXBElement<String>(_DeliveryMedia_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AdjType")
    public JAXBElement<String> createAdjType(String value) {
        return new JAXBElement<String>(_AdjType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimIntermediateStacker")
    public JAXBElement<String> createDevCimIntermediateStacker(String value) {
        return new JAXBElement<String>(_DevCimIntermediateStacker_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CreditType")
    public JAXBElement<String> createCreditType(String value) {
        return new JAXBElement<String>(_CreditType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "VerifiedByVisa")
    public JAXBElement<String> createVerifiedByVisa(String value) {
        return new JAXBElement<String>(_VerifiedByVisa_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EmailType")
    public JAXBElement<String> createEmailType(String value) {
        return new JAXBElement<String>(_EmailType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SvcName")
    public JAXBElement<String> createSvcName(String value) {
        return new JAXBElement<String>(_SvcName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RestartType")
    public JAXBElement<String> createRestartType(String value) {
        return new JAXBElement<String>(_RestartType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PostingSessionStatusCode")
    public JAXBElement<String> createPostingSessionStatusCode(String value) {
        return new JAXBElement<String>(_PostingSessionStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RelType")
    public JAXBElement<String> createRelType(String value) {
        return new JAXBElement<String>(_RelType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OverrideType")
    public JAXBElement<String> createOverrideType(String value) {
        return new JAXBElement<String>(_OverrideType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MediaTrnType")
    public JAXBElement<String> createMediaTrnType(String value) {
        return new JAXBElement<String>(_MediaTrnType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevTtuOperation")
    public JAXBElement<String> createDevTtuOperation(String value) {
        return new JAXBElement<String>(_DevTtuOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IdentConditions")
    public JAXBElement<String> createIdentConditions(String value) {
        return new JAXBElement<String>(_IdentConditions_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CompanyNameInd")
    public JAXBElement<String> createCompanyNameInd(String value) {
        return new JAXBElement<String>(_CompanyNameInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuPassbookGuidelight")
    public JAXBElement<String> createDevSiuPassbookGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuPassbookGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CharType")
    public JAXBElement<String> createCharType(String value) {
        return new JAXBElement<String>(_CharType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuCoinOutGuidelight")
    public JAXBElement<String> createDevSiuCoinOutGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuCoinOutGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmShutter")
    public JAXBElement<String> createDevCdmShutter(String value) {
        return new JAXBElement<String>(_DevCdmShutter_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BillSummAmtCode")
    public JAXBElement<String> createBillSummAmtCode(String value) {
        return new JAXBElement<String>(_BillSummAmtCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmTransportItems")
    public JAXBElement<String> createDevCdmTransportItems(String value) {
        return new JAXBElement<String>(_DevCdmTransportItems_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevPINType")
    public JAXBElement<String> createDevPINType(String value) {
        return new JAXBElement<String>(_DevPINType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevAlmOperation")
    public JAXBElement<String> createDevAlmOperation(String value) {
        return new JAXBElement<String>(_DevAlmOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimSafeDoor")
    public JAXBElement<String> createDevCimSafeDoor(String value) {
        return new JAXBElement<String>(_DevCimSafeDoor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustStatusCode")
    public JAXBElement<String> createCustStatusCode(String value) {
        return new JAXBElement<String>(_CustStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EanCode")
    public JAXBElement<String> createEanCode(String value) {
        return new JAXBElement<String>(_EanCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MassiveProcess")
    public JAXBElement<String> createMassiveProcess(String value) {
        return new JAXBElement<String>(_MassiveProcess_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CompositeCurAmtType")
    public JAXBElement<String> createCompositeCurAmtType(String value) {
        return new JAXBElement<String>(_CompositeCurAmtType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TrnSrc")
    public JAXBElement<String> createTrnSrc(String value) {
        return new JAXBElement<String>(_TrnSrc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SessionType")
    public JAXBElement<String> createSessionType(String value) {
        return new JAXBElement<String>(_SessionType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PSSCATSecLevel")
    public JAXBElement<String> createPSSCATSecLevel(String value) {
        return new JAXBElement<String>(_PSSCATSecLevel_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TrademarkInd")
    public JAXBElement<String> createTrademarkInd(String value) {
        return new JAXBElement<String>(_TrademarkInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtEnclType")
    public JAXBElement<String> createPmtEnclType(String value) {
        return new JAXBElement<String>(_PmtEnclType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NameAddrType")
    public JAXBElement<String> createNameAddrType(String value) {
        return new JAXBElement<String>(_NameAddrType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IdentVerifyMethod")
    public JAXBElement<String> createIdentVerifyMethod(String value) {
        return new JAXBElement<String>(_IdentVerifyMethod_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuSeismic")
    public JAXBElement<String> createDevSiuSeismic(String value) {
        return new JAXBElement<String>(_DevSiuSeismic_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChksumType")
    public JAXBElement<String> createChksumType(String value) {
        return new JAXBElement<String>(_ChksumType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ExtBalType")
    public JAXBElement<String> createExtBalType(String value) {
        return new JAXBElement<String>(_ExtBalType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Action")
    public JAXBElement<String> createAction(String value) {
        return new JAXBElement<String>(_Action_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuHeat")
    public JAXBElement<String> createDevSiuHeat(String value) {
        return new JAXBElement<String>(_DevSiuHeat_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CardPrefType")
    public JAXBElement<String> createCardPrefType(String value) {
        return new JAXBElement<String>(_CardPrefType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuAudibleAlarm")
    public JAXBElement<String> createDevSiuAudibleAlarm(String value) {
        return new JAXBElement<String>(_DevSiuAudibleAlarm_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCamType")
    public JAXBElement<String> createDevCamType(String value) {
        return new JAXBElement<String>(_DevCamType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BankAcctFeatLimitType")
    public JAXBElement<String> createBankAcctFeatLimitType(String value) {
        return new JAXBElement<String>(_BankAcctFeatLimitType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimType")
    public JAXBElement<String> createDevCimType(String value) {
        return new JAXBElement<String>(_DevCimType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DeliveryDestination")
    public JAXBElement<String> createDeliveryDestination(String value) {
        return new JAXBElement<String>(_DeliveryDestination_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AckType")
    public JAXBElement<String> createAckType(String value) {
        return new JAXBElement<String>(_AckType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimCashUnits")
    public JAXBElement<String> createDevCimCashUnits(String value) {
        return new JAXBElement<String>(_DevCimCashUnits_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CreditAuthType")
    public JAXBElement<String> createCreditAuthType(String value) {
        return new JAXBElement<String>(_CreditAuthType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CurAmtFeeStatus")
    public JAXBElement<String> createCurAmtFeeStatus(String value) {
        return new JAXBElement<String>(_CurAmtFeeStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EducationalLevel")
    public JAXBElement<String> createEducationalLevel(String value) {
        return new JAXBElement<String>(_EducationalLevel_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StmtSummType")
    public JAXBElement<String> createStmtSummType(String value) {
        return new JAXBElement<String>(_StmtSummType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RefType")
    public JAXBElement<String> createRefType(String value) {
        return new JAXBElement<String>(_RefType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "POSOperation")
    public JAXBElement<String> createPOSOperation(String value) {
        return new JAXBElement<String>(_POSOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PurchasePortfolioInd")
    public JAXBElement<String> createPurchasePortfolioInd(String value) {
        return new JAXBElement<String>(_PurchasePortfolioInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OptSupt")
    public JAXBElement<String> createOptSupt(String value) {
        return new JAXBElement<String>(_OptSupt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MediaSubType")
    public JAXBElement<String> createMediaSubType(String value) {
        return new JAXBElement<String>(_MediaSubType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SecObjStatusCode")
    public JAXBElement<String> createSecObjStatusCode(String value) {
        return new JAXBElement<String>(_SecObjStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GovIssueIdentType")
    public JAXBElement<String> createGovIssueIdentType(String value) {
        return new JAXBElement<String>(_GovIssueIdentType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BranchPreventiveLock")
    public JAXBElement<String> createBranchPreventiveLock(String value) {
        return new JAXBElement<String>(_BranchPreventiveLock_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuOperation")
    public JAXBElement<String> createDevSiuOperation(String value) {
        return new JAXBElement<String>(_DevSiuOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CertCode")
    public JAXBElement<String> createCertCode(String value) {
        return new JAXBElement<String>(_CertCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevSiuCimGuidelight")
    public JAXBElement<String> createDevSiuCimGuidelight(String value) {
        return new JAXBElement<String>(_DevSiuCimGuidelight_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmPositionLocation")
    public JAXBElement<String> createDevCdmPositionLocation(String value) {
        return new JAXBElement<String>(_DevCdmPositionLocation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BillPmtStatusCode")
    public JAXBElement<String> createBillPmtStatusCode(String value) {
        return new JAXBElement<String>(_BillPmtStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevIdcType")
    public JAXBElement<String> createDevIdcType(String value) {
        return new JAXBElement<String>(_DevIdcType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DepBkOrdStatusCode")
    public JAXBElement<String> createDepBkOrdStatusCode(String value) {
        return new JAXBElement<String>(_DepBkOrdStatusCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AuthSupt")
    public JAXBElement<String> createAuthSupt(String value) {
        return new JAXBElement<String>(_AuthSupt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimPositionLocation")
    public JAXBElement<String> createDevCimPositionLocation(String value) {
        return new JAXBElement<String>(_DevCimPositionLocation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustBankPrefSupt")
    public JAXBElement<String> createCustBankPrefSupt(String value) {
        return new JAXBElement<String>(_CustBankPrefSupt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Code")
    public JAXBElement<String> createCode(String value) {
        return new JAXBElement<String>(_Code_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IntegralLock")
    public JAXBElement<String> createIntegralLock(String value) {
        return new JAXBElement<String>(_IntegralLock_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TerminalType")
    public JAXBElement<String> createTerminalType(String value) {
        return new JAXBElement<String>(_TerminalType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SecObjPurpose")
    public JAXBElement<String> createSecObjPurpose(String value) {
        return new JAXBElement<String>(_SecObjPurpose_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PrcDtAdj")
    public JAXBElement<String> createPrcDtAdj(String value) {
        return new JAXBElement<String>(_PrcDtAdj_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SocialStratum")
    public JAXBElement<String> createSocialStratum(String value) {
        return new JAXBElement<String>(_SocialStratum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PhoneType")
    public JAXBElement<String> createPhoneType(String value) {
        return new JAXBElement<String>(_PhoneType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CSPhoneNum")
    public JAXBElement<String> createCSPhoneNum(String value) {
        return new JAXBElement<String>(_CSPhoneNum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ActivationPhone")
    public JAXBElement<String> createActivationPhone(String value) {
        return new JAXBElement<String>(_ActivationPhone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SafePhone")
    public JAXBElement<String> createSafePhone(String value) {
        return new JAXBElement<String>(_SafePhone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Phone")
    public JAXBElement<String> createPhone(String value) {
        return new JAXBElement<String>(_Phone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrgPhone")
    public JAXBElement<String> createOrgPhone(String value) {
        return new JAXBElement<String>(_OrgPhone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Fax")
    public JAXBElement<String> createFax(String value) {
        return new JAXBElement<String>(_Fax_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EvePhone")
    public JAXBElement<String> createEvePhone(String value) {
        return new JAXBElement<String>(_EvePhone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EveFax")
    public JAXBElement<String> createEveFax(String value) {
        return new JAXBElement<String>(_EveFax_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DayPhone")
    public JAXBElement<String> createDayPhone(String value) {
        return new JAXBElement<String>(_DayPhone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DayFax")
    public JAXBElement<String> createDayFax(String value) {
        return new JAXBElement<String>(_DayFax_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherIdentDocType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OtherIdentDoc")
    public JAXBElement<OtherIdentDocType> createOtherIdentDoc(OtherIdentDocType value) {
        return new JAXBElement<OtherIdentDocType>(_OtherIdentDoc_QNAME, OtherIdentDocType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Country")
    public JAXBElement<String> createCountry(String value) {
        return new JAXBElement<String>(_Country_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CityId")
    public JAXBElement<String> createCityId(String value) {
        return new JAXBElement<String>(_CityId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "City")
    public JAXBElement<String> createCity(String value) {
        return new JAXBElement<String>(_City_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StateProv")
    public JAXBElement<String> createStateProv(String value) {
        return new JAXBElement<String>(_StateProv_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TypeId")
    public JAXBElement<String> createTypeId(String value) {
        return new JAXBElement<String>(_TypeId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "HoldCoId")
    public JAXBElement<String> createHoldCoId(String value) {
        return new JAXBElement<String>(_HoldCoId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "VBV")
    public JAXBElement<String> createVBV(String value) {
        return new JAXBElement<String>(_VBV_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DepBkStyleId")
    public JAXBElement<String> createDepBkStyleId(String value) {
        return new JAXBElement<String>(_DepBkStyleId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TerminalObjId")
    public JAXBElement<String> createTerminalObjId(String value) {
        return new JAXBElement<String>(_TerminalObjId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SessionId")
    public JAXBElement<String> createSessionId(String value) {
        return new JAXBElement<String>(_SessionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CSPRefId")
    public JAXBElement<String> createCSPRefId(String value) {
        return new JAXBElement<String>(_CSPRefId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RecChkOrdId")
    public JAXBElement<String> createRecChkOrdId(String value) {
        return new JAXBElement<String>(_RecChkOrdId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RemitRefId")
    public JAXBElement<String> createRemitRefId(String value) {
        return new JAXBElement<String>(_RemitRefId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CompRemitStmtId")
    public JAXBElement<String> createCompRemitStmtId(String value) {
        return new JAXBElement<String>(_CompRemitStmtId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtId")
    public JAXBElement<String> createPmtId(String value) {
        return new JAXBElement<String>(_PmtId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChkBkStyleId")
    public JAXBElement<String> createChkBkStyleId(String value) {
        return new JAXBElement<String>(_ChkBkStyleId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Serial")
    public JAXBElement<String> createSerial(String value) {
        return new JAXBElement<String>(_Serial_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NodeIdTo")
    public JAXBElement<String> createNodeIdTo(String value) {
        return new JAXBElement<String>(_NodeIdTo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CIIU")
    public JAXBElement<String> createCIIU(String value) {
        return new JAXBElement<String>(_CIIU_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChkAcceptId")
    public JAXBElement<String> createChkAcceptId(String value) {
        return new JAXBElement<String>(_ChkAcceptId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ApprovalId")
    public JAXBElement<String> createApprovalId(String value) {
        return new JAXBElement<String>(_ApprovalId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PartyAcctRelId")
    public JAXBElement<String> createPartyAcctRelId(String value) {
        return new JAXBElement<String>(_PartyAcctRelId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MediaAcctId")
    public JAXBElement<String> createMediaAcctId(String value) {
        return new JAXBElement<String>(_MediaAcctId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ForExRateId")
    public JAXBElement<String> createForExRateId(String value) {
        return new JAXBElement<String>(_ForExRateId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "XferId")
    public JAXBElement<String> createXferId(String value) {
        return new JAXBElement<String>(_XferId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DepBkOrdId")
    public JAXBElement<String> createDepBkOrdId(String value) {
        return new JAXBElement<String>(_DepBkOrdId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TerminalId")
    public JAXBElement<String> createTerminalId(String value) {
        return new JAXBElement<String>(_TerminalId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ServerTerminalSeqId")
    public JAXBElement<String> createServerTerminalSeqId(String value) {
        return new JAXBElement<String>(_ServerTerminalSeqId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CreditMediaItemId")
    public JAXBElement<String> createCreditMediaItemId(String value) {
        return new JAXBElement<String>(_CreditMediaItemId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PurchItemId")
    public JAXBElement<String> createPurchItemId(String value) {
        return new JAXBElement<String>(_PurchItemId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RemitId")
    public JAXBElement<String> createRemitId(String value) {
        return new JAXBElement<String>(_RemitId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CompositeCurAmtId")
    public JAXBElement<String> createCompositeCurAmtId(String value) {
        return new JAXBElement<String>(_CompositeCurAmtId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtEnclId")
    public JAXBElement<String> createPmtEnclId(String value) {
        return new JAXBElement<String>(_PmtEnclId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RevClientTrnSeq")
    public JAXBElement<String> createRevClientTrnSeq(String value) {
        return new JAXBElement<String>(_RevClientTrnSeq_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NodeIdFrom")
    public JAXBElement<String> createNodeIdFrom(String value) {
        return new JAXBElement<String>(_NodeIdFrom_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ClientTrnSeq")
    public JAXBElement<String> createClientTrnSeq(String value) {
        return new JAXBElement<String>(_ClientTrnSeq_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CardPrefId")
    public JAXBElement<String> createCardPrefId(String value) {
        return new JAXBElement<String>(_CardPrefId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ProductNum")
    public JAXBElement<String> createProductNum(String value) {
        return new JAXBElement<String>(_ProductNum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AgentId")
    public JAXBElement<String> createAgentId(String value) {
        return new JAXBElement<String>(_AgentId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtAuthId")
    public JAXBElement<String> createPmtAuthId(String value) {
        return new JAXBElement<String>(_PmtAuthId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrgIdNum")
    public JAXBElement<String> createOrgIdNum(String value) {
        return new JAXBElement<String>(_OrgIdNum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MediaAcctAdjId")
    public JAXBElement<String> createMediaAcctAdjId(String value) {
        return new JAXBElement<String>(_MediaAcctAdjId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ForExDealId")
    public JAXBElement<String> createForExDealId(String value) {
        return new JAXBElement<String>(_ForExDealId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ProductCode")
    public JAXBElement<String> createProductCode(String value) {
        return new JAXBElement<String>(_ProductCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "VoucherTypeId")
    public JAXBElement<String> createVoucherTypeId(String value) {
        return new JAXBElement<String>(_VoucherTypeId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DepAppId")
    public JAXBElement<String> createDepAppId(String value) {
        return new JAXBElement<String>(_DepAppId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StopChkId")
    public JAXBElement<String> createStopChkId(String value) {
        return new JAXBElement<String>(_StopChkId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DepAcctStmtId")
    public JAXBElement<String> createDepAcctStmtId(String value) {
        return new JAXBElement<String>(_DepAcctStmtId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SecSignId")
    public JAXBElement<String> createSecSignId(String value) {
        return new JAXBElement<String>(_SecSignId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CreditId")
    public JAXBElement<String> createCreditId(String value) {
        return new JAXBElement<String>(_CreditId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PurchId")
    public JAXBElement<String> createPurchId(String value) {
        return new JAXBElement<String>(_PurchId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RelatedCreditId")
    public JAXBElement<String> createRelatedCreditId(String value) {
        return new JAXBElement<String>(_RelatedCreditId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ClubAcctId")
    public JAXBElement<String> createClubAcctId(String value) {
        return new JAXBElement<String>(_ClubAcctId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtBatchId")
    public JAXBElement<String> createPmtBatchId(String value) {
        return new JAXBElement<String>(_PmtBatchId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ProductId")
    public JAXBElement<String> createProductId(String value) {
        return new JAXBElement<String>(_ProductId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NodeId")
    public JAXBElement<String> createNodeId(String value) {
        return new JAXBElement<String>(_NodeId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BatchId")
    public JAXBElement<String> createBatchId(String value) {
        return new JAXBElement<String>(_BatchId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CardId")
    public JAXBElement<String> createCardId(String value) {
        return new JAXBElement<String>(_CardId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AcctPayAcctId")
    public JAXBElement<String> createAcctPayAcctId(String value) {
        return new JAXBElement<String>(_AcctPayAcctId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PayorAcctId")
    public JAXBElement<String> createPayorAcctId(String value) {
        return new JAXBElement<String>(_PayorAcctId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Org")
    public JAXBElement<String> createOrg(String value) {
        return new JAXBElement<String>(_Org_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LockboxAcctId")
    public JAXBElement<String> createLockboxAcctId(String value) {
        return new JAXBElement<String>(_LockboxAcctId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FontId")
    public JAXBElement<String> createFontId(String value) {
        return new JAXBElement<String>(_FontId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TrnAuthId")
    public JAXBElement<String> createTrnAuthId(String value) {
        return new JAXBElement<String>(_TrnAuthId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SPRefId")
    public JAXBElement<String> createSPRefId(String value) {
        return new JAXBElement<String>(_SPRefId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DebitId")
    public JAXBElement<String> createDebitId(String value) {
        return new JAXBElement<String>(_DebitId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SecretId")
    public JAXBElement<String> createSecretId(String value) {
        return new JAXBElement<String>(_SecretId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CreditAuthId")
    public JAXBElement<String> createCreditAuthId(String value) {
        return new JAXBElement<String>(_CreditAuthId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PostingSessionId")
    public JAXBElement<String> createPostingSessionId(String value) {
        return new JAXBElement<String>(_PostingSessionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RefId")
    public JAXBElement<String> createRefId(String value) {
        return new JAXBElement<String>(_RefId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ClientTerminalSeqId")
    public JAXBElement<String> createClientTerminalSeqId(String value) {
        return new JAXBElement<String>(_ClientTerminalSeqId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DeathCertificateId")
    public JAXBElement<String> createDeathCertificateId(String value) {
        return new JAXBElement<String>(_DeathCertificateId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Identifier")
    public JAXBElement<String> createIdentifier(String value) {
        return new JAXBElement<String>(_Identifier_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AccountantCenter")
    public JAXBElement<String> createAccountantCenter(String value) {
        return new JAXBElement<String>(_AccountantCenter_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BillSummAmtId")
    public JAXBElement<String> createBillSummAmtId(String value) {
        return new JAXBElement<String>(_BillSummAmtId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AcctPayAcct")
    public JAXBElement<String> createAcctPayAcct(String value) {
        return new JAXBElement<String>(_AcctPayAcct_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PassbkLastItemPrinted")
    public JAXBElement<String> createPassbkLastItemPrinted(String value) {
        return new JAXBElement<String>(_PassbkLastItemPrinted_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NewToken")
    public JAXBElement<String> createNewToken(String value) {
        return new JAXBElement<String>(_NewToken_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "InvItemId")
    public JAXBElement<String> createInvItemId(String value) {
        return new JAXBElement<String>(_InvItemId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TrnSeq")
    public JAXBElement<String> createTrnSeq(String value) {
        return new JAXBElement<String>(_TrnSeq_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EMVCardId")
    public JAXBElement<String> createEMVCardId(String value) {
        return new JAXBElement<String>(_EMVCardId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FileId")
    public JAXBElement<String> createFileId(String value) {
        return new JAXBElement<String>(_FileId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Token")
    public JAXBElement<String> createToken(String value) {
        return new JAXBElement<String>(_Token_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SPName")
    public JAXBElement<String> createSPName(String value) {
        return new JAXBElement<String>(_SPName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DebitAuthId")
    public JAXBElement<String> createDebitAuthId(String value) {
        return new JAXBElement<String>(_DebitAuthId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SecObjId")
    public JAXBElement<String> createSecObjId(String value) {
        return new JAXBElement<String>(_SecObjId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CreateRefId")
    public JAXBElement<String> createCreateRefId(String value) {
        return new JAXBElement<String>(_CreateRefId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtRemitRefId")
    public JAXBElement<String> createPmtRemitRefId(String value) {
        return new JAXBElement<String>(_PmtRemitRefId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RecXferId")
    public JAXBElement<String> createRecXferId(String value) {
        return new JAXBElement<String>(_RecXferId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChksumId")
    public JAXBElement<String> createChksumId(String value) {
        return new JAXBElement<String>(_ChksumId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PriorizationCode")
    public JAXBElement<String> createPriorizationCode(String value) {
        return new JAXBElement<String>(_PriorizationCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ParticipantId")
    public JAXBElement<String> createParticipantId(String value) {
        return new JAXBElement<String>(_ParticipantId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BillerNum")
    public JAXBElement<String> createBillerNum(String value) {
        return new JAXBElement<String>(_BillerNum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SubSegmentId")
    public JAXBElement<String> createSubSegmentId(String value) {
        return new JAXBElement<String>(_SubSegmentId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PhoneId")
    public JAXBElement<String> createPhoneId(String value) {
        return new JAXBElement<String>(_PhoneId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PassbkItemId")
    public JAXBElement<String> createPassbkItemId(String value) {
        return new JAXBElement<String>(_PassbkItemId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NetworkRefId")
    public JAXBElement<String> createNetworkRefId(String value) {
        return new JAXBElement<String>(_NetworkRefId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IdentProgramId")
    public JAXBElement<String> createIdentProgramId(String value) {
        return new JAXBElement<String>(_IdentProgramId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevId")
    public JAXBElement<String> createDevId(String value) {
        return new JAXBElement<String>(_DevId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BiometricCode")
    public JAXBElement<String> createBiometricCode(String value) {
        return new JAXBElement<String>(_BiometricCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TerminalSPObjId")
    public JAXBElement<String> createTerminalSPObjId(String value) {
        return new JAXBElement<String>(_TerminalSPObjId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SessionLoginId")
    public JAXBElement<String> createSessionLoginId(String value) {
        return new JAXBElement<String>(_SessionLoginId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustPayeeId")
    public JAXBElement<String> createCustPayeeId(String value) {
        return new JAXBElement<String>(_CustPayeeId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EmailId")
    public JAXBElement<String> createEmailId(String value) {
        return new JAXBElement<String>(_EmailId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RecPmtId")
    public JAXBElement<String> createRecPmtId(String value) {
        return new JAXBElement<String>(_RecPmtId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SecEncryptId")
    public JAXBElement<String> createSecEncryptId(String value) {
        return new JAXBElement<String>(_SecEncryptId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CoverStyleId")
    public JAXBElement<String> createCoverStyleId(String value) {
        return new JAXBElement<String>(_CoverStyleId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PmtRefId")
    public JAXBElement<String> createPmtRefId(String value) {
        return new JAXBElement<String>(_PmtRefId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChkOrdId")
    public JAXBElement<String> createChkOrdId(String value) {
        return new JAXBElement<String>(_ChkOrdId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OTP")
    public JAXBElement<String> createOTP(String value) {
        return new JAXBElement<String>(_OTP_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ContactId")
    public JAXBElement<String> createContactId(String value) {
        return new JAXBElement<String>(_ContactId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BankSvcChkSumId")
    public JAXBElement<String> createBankSvcChkSumId(String value) {
        return new JAXBElement<String>(_BankSvcChkSumId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SegmentId")
    public JAXBElement<String> createSegmentId(String value) {
        return new JAXBElement<String>(_SegmentId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PassbkId")
    public JAXBElement<String> createPassbkId(String value) {
        return new JAXBElement<String>(_PassbkId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MediaContainerId")
    public JAXBElement<String> createMediaContainerId(String value) {
        return new JAXBElement<String>(_MediaContainerId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StartDt")
    public JAXBElement<String> createStartDt(String value) {
        return new JAXBElement<String>(_StartDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PlatformXfsTimestamp")
    public JAXBElement<String> createPlatformXfsTimestamp(String value) {
        return new JAXBElement<String>(_PlatformXfsTimestamp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ExpDt")
    public JAXBElement<String> createExpDt(String value) {
        return new JAXBElement<String>(_ExpDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IssDt")
    public JAXBElement<String> createIssDt(String value) {
        return new JAXBElement<String>(_IssDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CreditHeldAmtExpDt")
    public JAXBElement<String> createCreditHeldAmtExpDt(String value) {
        return new JAXBElement<String>(_CreditHeldAmtExpDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ClientCreateDt")
    public JAXBElement<String> createClientCreateDt(String value) {
        return new JAXBElement<String>(_ClientCreateDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SessionDt")
    public JAXBElement<String> createSessionDt(String value) {
        return new JAXBElement<String>(_SessionDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrigIssueDt")
    public JAXBElement<String> createOrigIssueDt(String value) {
        return new JAXBElement<String>(_OrigIssueDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EndDt")
    public JAXBElement<String> createEndDt(String value) {
        return new JAXBElement<String>(_EndDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ForExValDt")
    public JAXBElement<String> createForExValDt(String value) {
        return new JAXBElement<String>(_ForExValDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CreatedDt")
    public JAXBElement<String> createCreatedDt(String value) {
        return new JAXBElement<String>(_CreatedDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ClientBusinessDt")
    public JAXBElement<String> createClientBusinessDt(String value) {
        return new JAXBElement<String>(_ClientBusinessDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MsgDt")
    public JAXBElement<String> createMsgDt(String value) {
        return new JAXBElement<String>(_MsgDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ServerDt")
    public JAXBElement<String> createServerDt(String value) {
        return new JAXBElement<String>(_ServerDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ExpectedAvailDt")
    public JAXBElement<String> createExpectedAvailDt(String value) {
        return new JAXBElement<String>(_ExpectedAvailDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NextDt")
    public JAXBElement<String> createNextDt(String value) {
        return new JAXBElement<String>(_NextDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EffDt")
    public JAXBElement<String> createEffDt(String value) {
        return new JAXBElement<String>(_EffDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ClientDt")
    public JAXBElement<String> createClientDt(String value) {
        return new JAXBElement<String>(_ClientDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ExpectedOpenDt")
    public JAXBElement<String> createExpectedOpenDt(String value) {
        return new JAXBElement<String>(_ExpectedOpenDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AvailDt")
    public JAXBElement<String> createAvailDt(String value) {
        return new JAXBElement<String>(_AvailDt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Summary")
    public JAXBElement<String> createSummary(String value) {
        return new JAXBElement<String>(_Summary_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RejectionDesc")
    public JAXBElement<String> createRejectionDesc(String value) {
        return new JAXBElement<String>(_RejectionDesc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LongCodeDesc")
    public JAXBElement<String> createLongCodeDesc(String value) {
        return new JAXBElement<String>(_LongCodeDesc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TargetChnValue")
    public JAXBElement<String> createTargetChnValue(String value) {
        return new JAXBElement<String>(_TargetChnValue_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TargetChnNick")
    public JAXBElement<String> createTargetChnNick(String value) {
        return new JAXBElement<String>(_TargetChnNick_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ShortCodeDesc")
    public JAXBElement<String> createShortCodeDesc(String value) {
        return new JAXBElement<String>(_ShortCodeDesc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OtherIncomeDesc")
    public JAXBElement<String> createOtherIncomeDesc(String value) {
        return new JAXBElement<String>(_OtherIncomeDesc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EdLevel")
    public JAXBElement<String> createEdLevel(String value) {
        return new JAXBElement<String>(_EdLevel_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Desc")
    public JAXBElement<String> createDesc(String value) {
        return new JAXBElement<String>(_Desc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustTypeDesc")
    public JAXBElement<String> createCustTypeDesc(String value) {
        return new JAXBElement<String>(_CustTypeDesc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneralExceptionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GeneralException")
    public JAXBElement<GeneralExceptionType> createGeneralException(GeneralExceptionType value) {
        return new JAXBElement<GeneralExceptionType>(_GeneralException_QNAME, GeneralExceptionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TrnRqUID")
    public JAXBElement<String> createTrnRqUID(String value) {
        return new JAXBElement<String>(_TrnRqUID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RelatedItemInqRqUID")
    public JAXBElement<String> createRelatedItemInqRqUID(String value) {
        return new JAXBElement<String>(_RelatedItemInqRqUID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BillId")
    public JAXBElement<String> createBillId(String value) {
        return new JAXBElement<String>(_BillId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RqUID")
    public JAXBElement<String> createRqUID(String value) {
        return new JAXBElement<String>(_RqUID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DiscId")
    public JAXBElement<String> createDiscId(String value) {
        return new JAXBElement<String>(_DiscId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SvcRqUID")
    public JAXBElement<String> createSvcRqUID(String value) {
        return new JAXBElement<String>(_SvcRqUID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ReconRqUID")
    public JAXBElement<String> createReconRqUID(String value) {
        return new JAXBElement<String>(_ReconRqUID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AsyncRqUID")
    public JAXBElement<String> createAsyncRqUID(String value) {
        return new JAXBElement<String>(_AsyncRqUID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Status")
    public JAXBElement<StatusType> createStatus(StatusType value) {
        return new JAXBElement<StatusType>(_Status_QNAME, StatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AsyncRsInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AsyncRsInfo")
    public JAXBElement<AsyncRsInfoType> createAsyncRsInfo(AsyncRsInfoType value) {
        return new JAXBElement<AsyncRsInfoType>(_AsyncRsInfo_QNAME, AsyncRsInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommercialAreaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CommercialArea")
    public JAXBElement<CommercialAreaType> createCommercialArea(CommercialAreaType value) {
        return new JAXBElement<CommercialAreaType>(_CommercialArea_QNAME, CommercialAreaType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FinalPrcDt")
    public JAXBElement<XMLGregorianCalendar> createFinalPrcDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_FinalPrcDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DepMatureDt")
    public JAXBElement<XMLGregorianCalendar> createDepMatureDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DepMatureDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChkClrDt")
    public JAXBElement<XMLGregorianCalendar> createChkClrDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ChkClrDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TrnDt")
    public JAXBElement<XMLGregorianCalendar> createTrnDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TrnDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AppExpDt")
    public JAXBElement<XMLGregorianCalendar> createAppExpDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AppExpDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ResolvePendingDt")
    public JAXBElement<XMLGregorianCalendar> createResolvePendingDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ResolvePendingDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PaidDt")
    public JAXBElement<XMLGregorianCalendar> createPaidDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_PaidDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrigDt")
    public JAXBElement<XMLGregorianCalendar> createOrigDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OrigDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastTrnDt")
    public JAXBElement<XMLGregorianCalendar> createLastTrnDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LastTrnDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastDepDt")
    public JAXBElement<XMLGregorianCalendar> createLastDepDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LastDepDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "HolDt")
    public JAXBElement<XMLGregorianCalendar> createHolDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_HolDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EstablishDt")
    public JAXBElement<XMLGregorianCalendar> createEstablishDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_EstablishDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DiscDt")
    public JAXBElement<XMLGregorianCalendar> createDiscDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DiscDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ClosedDt")
    public JAXBElement<XMLGregorianCalendar> createClosedDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClosedDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BillDt")
    public JAXBElement<XMLGregorianCalendar> createBillDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BillDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SessionPostingDt")
    public JAXBElement<XMLGregorianCalendar> createSessionPostingDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SessionPostingDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PostedDt")
    public JAXBElement<XMLGregorianCalendar> createPostedDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_PostedDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrigPmtDueDt")
    public JAXBElement<XMLGregorianCalendar> createOrigPmtDueDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OrigPmtDueDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MatDt")
    public JAXBElement<XMLGregorianCalendar> createMatDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MatDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastYrInsPaidDt")
    public JAXBElement<XMLGregorianCalendar> createLastYrInsPaidDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LastYrInsPaidDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastPmtDt")
    public JAXBElement<XMLGregorianCalendar> createLastPmtDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LastPmtDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ImportDt")
    public JAXBElement<XMLGregorianCalendar> createImportDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ImportDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FinalDueDt")
    public JAXBElement<XMLGregorianCalendar> createFinalDueDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_FinalDueDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DueDt")
    public JAXBElement<XMLGregorianCalendar> createDueDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DueDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CloseDt")
    public JAXBElement<XMLGregorianCalendar> createCloseDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CloseDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BirthDt")
    public JAXBElement<XMLGregorianCalendar> createBirthDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BirthDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TaxPaidDt")
    public JAXBElement<XMLGregorianCalendar> createTaxPaidDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TaxPaidDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AppEffDt")
    public JAXBElement<XMLGregorianCalendar> createAppEffDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AppEffDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PrcDt")
    public JAXBElement<XMLGregorianCalendar> createPrcDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_PrcDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrigPmtPrcDt")
    public JAXBElement<XMLGregorianCalendar> createOrigPmtPrcDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OrigPmtPrcDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OpenDt")
    public JAXBElement<XMLGregorianCalendar> createOpenDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OpenDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastYrTaxPaidDt")
    public JAXBElement<XMLGregorianCalendar> createLastYrTaxPaidDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LastYrTaxPaidDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastStmtDt")
    public JAXBElement<XMLGregorianCalendar> createLastStmtDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LastStmtDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "InsPaidDt")
    public JAXBElement<XMLGregorianCalendar> createInsPaidDt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_InsPaidDt_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FACTAInd")
    public JAXBElement<String> createFACTAInd(String value) {
        return new JAXBElement<String>(_FACTAInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GroupMemberInd")
    public JAXBElement<String> createGroupMemberInd(String value) {
        return new JAXBElement<String>(_GroupMemberInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Indicator")
    public JAXBElement<String> createIndicator(String value) {
        return new JAXBElement<String>(_Indicator_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IndicatorNational")
    public JAXBElement<String> createIndicatorNational(String value) {
        return new JAXBElement<String>(_IndicatorNational_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "YN")
    public JAXBElement<String> createYN(String value) {
        return new JAXBElement<String>(_YN_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SelfTaxWithholderInd")
    public JAXBElement<String> createSelfTaxWithholderInd(String value) {
        return new JAXBElement<String>(_SelfTaxWithholderInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SalesInd")
    public JAXBElement<String> createSalesInd(String value) {
        return new JAXBElement<String>(_SalesInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MainUsrInd")
    public JAXBElement<String> createMainUsrInd(String value) {
        return new JAXBElement<String>(_MainUsrInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GovOrgInqInd")
    public JAXBElement<String> createGovOrgInqInd(String value) {
        return new JAXBElement<String>(_GovOrgInqInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncomeTaxInd")
    public JAXBElement<String> createIncomeTaxInd(String value) {
        return new JAXBElement<String>(_IncomeTaxInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IndicatorExchange")
    public JAXBElement<String> createIndicatorExchange(String value) {
        return new JAXBElement<String>(_IndicatorExchange_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LargessTaxPayerInd")
    public JAXBElement<String> createLargessTaxPayerInd(String value) {
        return new JAXBElement<String>(_LargessTaxPayerInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ScoreInd")
    public JAXBElement<String> createScoreInd(String value) {
        return new JAXBElement<String>(_ScoreInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AuthInd")
    public JAXBElement<String> createAuthInd(String value) {
        return new JAXBElement<String>(_AuthInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ProcessInd")
    public JAXBElement<String> createProcessInd(String value) {
        return new JAXBElement<String>(_ProcessInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonClientType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PersonClient")
    public JAXBElement<PersonClientType> createPersonClient(PersonClientType value) {
        return new JAXBElement<PersonClientType>(_PersonClient_QNAME, PersonClientType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonNameType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PersonName")
    public JAXBElement<PersonNameType> createPersonName(PersonNameType value) {
        return new JAXBElement<PersonNameType>(_PersonName_QNAME, PersonNameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MiddleName")
    public JAXBElement<String> createMiddleName(String value) {
        return new JAXBElement<String>(_MiddleName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FirstName")
    public JAXBElement<String> createFirstName(String value) {
        return new JAXBElement<String>(_FirstName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TitlePrefix")
    public JAXBElement<String> createTitlePrefix(String value) {
        return new JAXBElement<String>(_TitlePrefix_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Nickname")
    public JAXBElement<String> createNickname(String value) {
        return new JAXBElement<String>(_Nickname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NameSuffix")
    public JAXBElement<String> createNameSuffix(String value) {
        return new JAXBElement<String>(_NameSuffix_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LegalName")
    public JAXBElement<String> createLegalName(String value) {
        return new JAXBElement<String>(_LegalName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastName")
    public JAXBElement<String> createLastName(String value) {
        return new JAXBElement<String>(_LastName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FullName")
    public JAXBElement<String> createFullName(String value) {
        return new JAXBElement<String>(_FullName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContactInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ContactInfo")
    public JAXBElement<ContactInfoType> createContactInfo(ContactInfoType value) {
        return new JAXBElement<ContactInfoType>(_ContactInfo_QNAME, ContactInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostAddrType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BillRetAddr")
    public JAXBElement<PostAddrType> createBillRetAddr(PostAddrType value) {
        return new JAXBElement<PostAddrType>(_BillRetAddr_QNAME, PostAddrType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostAddrType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PostAddr")
    public JAXBElement<PostAddrType> createPostAddr(PostAddrType value) {
        return new JAXBElement<PostAddrType>(_PostAddr_QNAME, PostAddrType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostAddrType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RemitAddr")
    public JAXBElement<PostAddrType> createRemitAddr(PostAddrType value) {
        return new JAXBElement<PostAddrType>(_RemitAddr_QNAME, PostAddrType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostAddrType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DeathInfoAddr")
    public JAXBElement<PostAddrType> createDeathInfoAddr(PostAddrType value) {
        return new JAXBElement<PostAddrType>(_DeathInfoAddr_QNAME, PostAddrType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Addr4")
    public JAXBElement<String> createAddr4(String value) {
        return new JAXBElement<String>(_Addr4_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Addr3")
    public JAXBElement<String> createAddr3(String value) {
        return new JAXBElement<String>(_Addr3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Addr2")
    public JAXBElement<String> createAddr2(String value) {
        return new JAXBElement<String>(_Addr2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Addr1")
    public JAXBElement<String> createAddr1(String value) {
        return new JAXBElement<String>(_Addr1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Coordenate")
    public JAXBElement<String> createCoordenate(String value) {
        return new JAXBElement<String>(_Coordenate_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ZipCode")
    public JAXBElement<String> createZipCode(String value) {
        return new JAXBElement<String>(_ZipCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SafeEmailAddr")
    public JAXBElement<String> createSafeEmailAddr(String value) {
        return new JAXBElement<String>(_SafeEmailAddr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "EmailAddr")
    public JAXBElement<String> createEmailAddr(String value) {
        return new JAXBElement<String>(_EmailAddr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DriversLicenseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DriversLicense")
    public JAXBElement<DriversLicenseType> createDriversLicense(DriversLicenseType value) {
        return new JAXBElement<DriversLicenseType>(_DriversLicense_QNAME, DriversLicenseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LicenseNum")
    public JAXBElement<String> createLicenseNum(String value) {
        return new JAXBElement<String>(_LicenseNum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResidencePlaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ResidencePlace")
    public JAXBElement<ResidencePlaceType> createResidencePlace(ResidencePlaceType value) {
        return new JAXBElement<ResidencePlaceType>(_ResidencePlace_QNAME, ResidencePlaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinantialInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FinantialInfo")
    public JAXBElement<FinantialInfoType> createFinantialInfo(FinantialInfoType value) {
        return new JAXBElement<FinantialInfoType>(_FinantialInfo_QNAME, FinantialInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LowCurAmt")
    public JAXBElement<CurrencyAmountType> createLowCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_LowCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PrepayPenalty")
    public JAXBElement<CurrencyAmountType> createPrepayPenalty(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_PrepayPenalty_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AmtY")
    public JAXBElement<CurrencyAmountType> createAmtY(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_AmtY_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastPmtCurAmt")
    public JAXBElement<CurrencyAmountType> createLastPmtCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_LastPmtCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NextPmtCurAmt")
    public JAXBElement<CurrencyAmountType> createNextPmtCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_NextPmtCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Outcome")
    public JAXBElement<CurrencyAmountType> createOutcome(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_Outcome_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Income")
    public JAXBElement<CurrencyAmountType> createIncome(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_Income_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChkCourtesyAmt")
    public JAXBElement<CurrencyAmountType> createChkCourtesyAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_ChkCourtesyAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TaxPaidCurAmt")
    public JAXBElement<CurrencyAmountType> createTaxPaidCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_TaxPaidCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DomXferFeeCurAmt")
    public JAXBElement<CurrencyAmountType> createDomXferFeeCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_DomXferFeeCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MaxCurAmt")
    public JAXBElement<CurrencyAmountType> createMaxCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_MaxCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PrevYrCurAmt")
    public JAXBElement<CurrencyAmountType> createPrevYrCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_PrevYrCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastYrInsPaidCurAmt")
    public JAXBElement<CurrencyAmountType> createLastYrInsPaidCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_LastYrInsPaidCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrigCurAmt")
    public JAXBElement<CurrencyAmountType> createOrigCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_OrigCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TotalLiabilities")
    public JAXBElement<CurrencyAmountType> createTotalLiabilities(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_TotalLiabilities_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "InitialCurAmt")
    public JAXBElement<CurrencyAmountType> createInitialCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_InitialCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IntlXferFeeCurAmt")
    public JAXBElement<CurrencyAmountType> createIntlXferFeeCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_IntlXferFeeCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TotalCurAmt")
    public JAXBElement<CurrencyAmountType> createTotalCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_TotalCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FinalCurAmt")
    public JAXBElement<CurrencyAmountType> createFinalCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_FinalCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MinAmtDue")
    public JAXBElement<CurrencyAmountType> createMinAmtDue(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_MinAmtDue_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RegPmtCurAmt")
    public JAXBElement<CurrencyAmountType> createRegPmtCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_RegPmtCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BalAmt")
    public JAXBElement<CurrencyAmountType> createBalAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_BalAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastYrTaxPaidCurAmt")
    public JAXBElement<CurrencyAmountType> createLastYrTaxPaidCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_LastYrTaxPaidCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PaidCurAmt")
    public JAXBElement<CurrencyAmountType> createPaidCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_PaidCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TotalAssets")
    public JAXBElement<CurrencyAmountType> createTotalAssets(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_TotalAssets_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "InitialDeposit")
    public JAXBElement<CurrencyAmountType> createInitialDeposit(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_InitialDeposit_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "InvoicePremium")
    public JAXBElement<CurrencyAmountType> createInvoicePremium(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_InvoicePremium_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NetCurAmt")
    public JAXBElement<CurrencyAmountType> createNetCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_NetCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OtherAssets")
    public JAXBElement<CurrencyAmountType> createOtherAssets(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_OtherAssets_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Sales")
    public JAXBElement<CurrencyAmountType> createSales(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_Sales_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "FeeAmt")
    public JAXBElement<CurrencyAmountType> createFeeAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_FeeAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MinCurAmt")
    public JAXBElement<CurrencyAmountType> createMinCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_MinCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SecAmt")
    public JAXBElement<CurrencyAmountType> createSecAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_SecAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChkEnteredAmt")
    public JAXBElement<CurrencyAmountType> createChkEnteredAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_ChkEnteredAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LOCLimit")
    public JAXBElement<CurrencyAmountType> createLOCLimit(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_LOCLimit_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PassbkLastBalPrinted")
    public JAXBElement<CurrencyAmountType> createPassbkLastBalPrinted(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_PassbkLastBalPrinted_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AmtX")
    public JAXBElement<CurrencyAmountType> createAmtX(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_AmtX_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "InsPaidCurAmt")
    public JAXBElement<CurrencyAmountType> createInsPaidCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_InsPaidCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NetSessionTotals")
    public JAXBElement<CurrencyAmountType> createNetSessionTotals(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_NetSessionTotals_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OtherIncome")
    public JAXBElement<CurrencyAmountType> createOtherIncome(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_OtherIncome_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "HighCurAmt")
    public JAXBElement<CurrencyAmountType> createHighCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_HighCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MinPmtCurAmt")
    public JAXBElement<CurrencyAmountType> createMinPmtCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_MinPmtCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CashAmt")
    public JAXBElement<CurrencyAmountType> createCashAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_CashAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "StmtRunningBal")
    public JAXBElement<CurrencyAmountType> createStmtRunningBal(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_StmtRunningBal_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CurAmt")
    public JAXBElement<CurrencyAmountType> createCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_CurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MillasFactor")
    public JAXBElement<BigDecimal> createMillasFactor(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MillasFactor_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Rate")
    public JAXBElement<BigDecimal> createRate(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Rate_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PlatformXfsRequestId")
    public JAXBElement<BigDecimal> createPlatformXfsRequestId(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PlatformXfsRequestId_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PlatformXfsAction")
    public JAXBElement<BigDecimal> createPlatformXfsAction(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PlatformXfsAction_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BankSvcChkSumValue")
    public JAXBElement<BigDecimal> createBankSvcChkSumValue(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BankSvcChkSumValue_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RandomNbr")
    public JAXBElement<BigDecimal> createRandomNbr(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_RandomNbr_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "WelcomePoints")
    public JAXBElement<BigDecimal> createWelcomePoints(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_WelcomePoints_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ExpRate")
    public JAXBElement<BigDecimal> createExpRate(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ExpRate_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PrePaidValue")
    public JAXBElement<BigDecimal> createPrePaidValue(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PrePaidValue_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PlatformXfsEventId")
    public JAXBElement<BigDecimal> createPlatformXfsEventId(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PlatformXfsEventId_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PayCurAmt")
    public JAXBElement<BigDecimal> createPayCurAmt(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PayCurAmt_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OwnerPercent")
    public JAXBElement<BigDecimal> createOwnerPercent(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_OwnerPercent_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RdmdPoints")
    public JAXBElement<BigDecimal> createRdmdPoints(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_RdmdPoints_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCdmBinDenomination")
    public JAXBElement<BigDecimal> createDevCdmBinDenomination(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DevCdmBinDenomination_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "MulRedMillas")
    public JAXBElement<BigDecimal> createMulRedMillas(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MulRedMillas_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PlatformXfsState")
    public JAXBElement<BigDecimal> createPlatformXfsState(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PlatformXfsState_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PlatformXfsCmdCode")
    public JAXBElement<BigDecimal> createPlatformXfsCmdCode(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PlatformXfsCmdCode_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ChksumValue")
    public JAXBElement<BigDecimal> createChksumValue(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ChksumValue_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Amt")
    public JAXBElement<BigDecimal> createAmt(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Amt_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IntAPY")
    public JAXBElement<BigDecimal> createIntAPY(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IntAPY_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TrnPoints")
    public JAXBElement<BigDecimal> createTrnPoints(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TrnPoints_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeathCertificateInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DeathCertificateInfo")
    public JAXBElement<DeathCertificateInfoType> createDeathCertificateInfo(DeathCertificateInfoType value) {
        return new JAXBElement<DeathCertificateInfoType>(_DeathCertificateInfo_QNAME, DeathCertificateInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelRangeDtType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SelRangeDueDt")
    public JAXBElement<SelRangeDtType> createSelRangeDueDt(SelRangeDtType value) {
        return new JAXBElement<SelRangeDtType>(_SelRangeDueDt_QNAME, SelRangeDtType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelRangeDtType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SelRangePrcDt")
    public JAXBElement<SelRangeDtType> createSelRangePrcDt(SelRangeDtType value) {
        return new JAXBElement<SelRangeDtType>(_SelRangePrcDt_QNAME, SelRangeDtType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelRangeDtType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SelRangeDt")
    public JAXBElement<SelRangeDtType> createSelRangeDt(SelRangeDtType value) {
        return new JAXBElement<SelRangeDtType>(_SelRangeDt_QNAME, SelRangeDtType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Memo")
    public JAXBElement<String> createMemo(String value) {
        return new JAXBElement<String>(_Memo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompositeContactInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CompositeContactInfo")
    public JAXBElement<CompositeContactInfoType> createCompositeContactInfo(CompositeContactInfoType value) {
        return new JAXBElement<CompositeContactInfoType>(_CompositeContactInfo_QNAME, CompositeContactInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseEnvrType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BaseEnvr")
    public JAXBElement<BaseEnvrType> createBaseEnvr(BaseEnvrType value) {
        return new JAXBElement<BaseEnvrType>(_BaseEnvr_QNAME, BaseEnvrType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SegmentName")
    public JAXBElement<String> createSegmentName(String value) {
        return new JAXBElement<String>(_SegmentName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IndustIdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IndustId")
    public JAXBElement<IndustIdType> createIndustId(IndustIdType value) {
        return new JAXBElement<IndustIdType>(_IndustId_QNAME, IndustIdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IndustNum")
    public JAXBElement<String> createIndustNum(String value) {
        return new JAXBElement<String>(_IndustNum_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustLoginId")
    public JAXBElement<String> createCustLoginId(String value) {
        return new JAXBElement<String>(_CustLoginId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "LastDepCurAmt")
    public JAXBElement<CurrencyAmountType> createLastDepCurAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_LastDepCurAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyAmountType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AuthAmt")
    public JAXBElement<CurrencyAmountType> createAuthAmt(CurrencyAmountType value) {
        return new JAXBElement<CurrencyAmountType>(_AuthAmt_QNAME, CurrencyAmountType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CurRate")
    public JAXBElement<BigDecimal> createCurRate(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CurRate_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevCimBinDenomination")
    public JAXBElement<BigDecimal> createDevCimBinDenomination(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DevCimBinDenomination_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RefInfo")
    public JAXBElement<RefInfoType> createRefInfo(RefInfoType value) {
        return new JAXBElement<RefInfoType>(_RefInfo_QNAME, RefInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ModPswdFirst")
    public JAXBElement<String> createModPswdFirst(String value) {
        return new JAXBElement<String>(_ModPswdFirst_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncToken")
    public JAXBElement<String> createIncToken(String value) {
        return new JAXBElement<String>(_IncToken_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncRefInfo")
    public JAXBElement<String> createIncRefInfo(String value) {
        return new JAXBElement<String>(_IncRefInfo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncExtBal")
    public JAXBElement<String> createIncExtBal(String value) {
        return new JAXBElement<String>(_IncExtBal_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GetSecureInfo")
    public JAXBElement<String> createGetSecureInfo(String value) {
        return new JAXBElement<String>(_GetSecureInfo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GetGeneralInfo")
    public JAXBElement<String> createGetGeneralInfo(String value) {
        return new JAXBElement<String>(_GetGeneralInfo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "TruncatedInd")
    public JAXBElement<String> createTruncatedInd(String value) {
        return new JAXBElement<String>(_TruncatedInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CSPCustInfoReq")
    public JAXBElement<String> createCSPCustInfoReq(String value) {
        return new JAXBElement<String>(_CSPCustInfoReq_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BackupWithholding")
    public JAXBElement<String> createBackupWithholding(String value) {
        return new JAXBElement<String>(_BackupWithholding_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AcceptReqd")
    public JAXBElement<String> createAcceptReqd(String value) {
        return new JAXBElement<String>(_AcceptReqd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SecretOptional")
    public JAXBElement<String> createSecretOptional(String value) {
        return new JAXBElement<String>(_SecretOptional_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RecPmtMod")
    public JAXBElement<String> createRecPmtMod(String value) {
        return new JAXBElement<String>(_RecPmtMod_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PrimaryAcct")
    public JAXBElement<String> createPrimaryAcct(String value) {
        return new JAXBElement<String>(_PrimaryAcct_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "POSCaptureCapability")
    public JAXBElement<String> createPOSCaptureCapability(String value) {
        return new JAXBElement<String>(_POSCaptureCapability_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ExternalPaymentImageAvailable")
    public JAXBElement<String> createExternalPaymentImageAvailable(String value) {
        return new JAXBElement<String>(_ExternalPaymentImageAvailable_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OpenAcctInd")
    public JAXBElement<String> createOpenAcctInd(String value) {
        return new JAXBElement<String>(_OpenAcctInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncCounts")
    public JAXBElement<String> createIncCounts(String value) {
        return new JAXBElement<String>(_IncCounts_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncAllItems")
    public JAXBElement<String> createIncAllItems(String value) {
        return new JAXBElement<String>(_IncAllItems_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GenSessKey")
    public JAXBElement<String> createGenSessKey(String value) {
        return new JAXBElement<String>(_GenSessKey_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevChkImageEnabled")
    public JAXBElement<String> createDevChkImageEnabled(String value) {
        return new JAXBElement<String>(_DevChkImageEnabled_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ModPending")
    public JAXBElement<String> createModPending(String value) {
        return new JAXBElement<String>(_ModPending_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NextDay")
    public JAXBElement<String> createNextDay(String value) {
        return new JAXBElement<String>(_NextDay_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncSummary")
    public JAXBElement<String> createIncSummary(String value) {
        return new JAXBElement<String>(_IncSummary_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncImages")
    public JAXBElement<String> createIncImages(String value) {
        return new JAXBElement<String>(_IncImages_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncEndrsmntStmp")
    public JAXBElement<String> createIncEndrsmntStmp(String value) {
        return new JAXBElement<String>(_IncEndrsmntStmp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GetLocationInfo")
    public JAXBElement<String> createGetLocationInfo(String value) {
        return new JAXBElement<String>(_GetLocationInfo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GetBasicInfo")
    public JAXBElement<String> createGetBasicInfo(String value) {
        return new JAXBElement<String>(_GetBasicInfo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SuppressEcho")
    public JAXBElement<String> createSuppressEcho(String value) {
        return new JAXBElement<String>(_SuppressEcho_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CaseSen")
    public JAXBElement<String> createCaseSen(String value) {
        return new JAXBElement<String>(_CaseSen_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AllocateAllowed")
    public JAXBElement<String> createAllocateAllowed(String value) {
        return new JAXBElement<String>(_AllocateAllowed_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SpaceAlwd")
    public JAXBElement<String> createSpaceAlwd(String value) {
        return new JAXBElement<String>(_SpaceAlwd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SecObjInitRemKey")
    public JAXBElement<String> createSecObjInitRemKey(String value) {
        return new JAXBElement<String>(_SecObjInitRemKey_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ProdPreAppr")
    public JAXBElement<String> createProdPreAppr(String value) {
        return new JAXBElement<String>(_ProdPreAppr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PrenoteReqd")
    public JAXBElement<String> createPrenoteReqd(String value) {
        return new JAXBElement<String>(_PrenoteReqd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "POSAttended")
    public JAXBElement<String> createPOSAttended(String value) {
        return new JAXBElement<String>(_POSAttended_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NotifyWilling")
    public JAXBElement<String> createNotifyWilling(String value) {
        return new JAXBElement<String>(_NotifyWilling_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncBillerContact")
    public JAXBElement<String> createIncBillerContact(String value) {
        return new JAXBElement<String>(_IncBillerContact_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ImmediateXfer")
    public JAXBElement<String> createImmediateXfer(String value) {
        return new JAXBElement<String>(_ImmediateXfer_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DupChkOverride")
    public JAXBElement<String> createDupChkOverride(String value) {
        return new JAXBElement<String>(_DupChkOverride_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DevAppNormalOperation")
    public JAXBElement<String> createDevAppNormalOperation(String value) {
        return new JAXBElement<String>(_DevAppNormalOperation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ManualMicr")
    public JAXBElement<String> createManualMicr(String value) {
        return new JAXBElement<String>(_ManualMicr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncSecSigLine")
    public JAXBElement<String> createIncSecSigLine(String value) {
        return new JAXBElement<String>(_IncSecSigLine_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncHistory")
    public JAXBElement<String> createIncHistory(String value) {
        return new JAXBElement<String>(_IncHistory_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncDisc")
    public JAXBElement<String> createIncDisc(String value) {
        return new JAXBElement<String>(_IncDisc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RetryInd")
    public JAXBElement<String> createRetryInd(String value) {
        return new JAXBElement<String>(_RetryInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GetFinantialInfo")
    public JAXBElement<String> createGetFinantialInfo(String value) {
        return new JAXBElement<String>(_GetFinantialInfo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "UpPayee")
    public JAXBElement<String> createUpPayee(String value) {
        return new JAXBElement<String>(_UpPayee_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustNameReqd")
    public JAXBElement<String> createCustNameReqd(String value) {
        return new JAXBElement<String>(_CustNameReqd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CascadeDel")
    public JAXBElement<String> createCascadeDel(String value) {
        return new JAXBElement<String>(_CascadeDel_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "AccountHolder")
    public JAXBElement<String> createAccountHolder(String value) {
        return new JAXBElement<String>(_AccountHolder_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SettleInd")
    public JAXBElement<String> createSettleInd(String value) {
        return new JAXBElement<String>(_SettleInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "RecXferMod")
    public JAXBElement<String> createRecXferMod(String value) {
        return new JAXBElement<String>(_RecXferMod_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PrintReceipt")
    public JAXBElement<String> createPrintReceipt(String value) {
        return new JAXBElement<String>(_PrintReceipt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "PostAddrReqd")
    public JAXBElement<String> createPostAddrReqd(String value) {
        return new JAXBElement<String>(_PostAddrReqd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OwnerInd")
    public JAXBElement<String> createOwnerInd(String value) {
        return new JAXBElement<String>(_OwnerInd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncDetail")
    public JAXBElement<String> createIncDetail(String value) {
        return new JAXBElement<String>(_IncDetail_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NotifyReqd")
    public JAXBElement<String> createNotifyReqd(String value) {
        return new JAXBElement<String>(_NotifyReqd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "IncBal")
    public JAXBElement<String> createIncBal(String value) {
        return new JAXBElement<String>(_IncBal_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "ImmediatePmt")
    public JAXBElement<String> createImmediatePmt(String value) {
        return new JAXBElement<String>(_ImmediatePmt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "DiscReqd")
    public JAXBElement<String> createDiscReqd(String value) {
        return new JAXBElement<String>(_DiscReqd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SvcRsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SvcRs")
    public JAXBElement<SvcRsType> createSvcRs(SvcRsType value) {
        return new JAXBElement<SvcRsType>(_SvcRs_QNAME, SvcRsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustIdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "UserId")
    public JAXBElement<CustIdType> createUserId(CustIdType value) {
        return new JAXBElement<CustIdType>(_UserId_QNAME, CustIdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustIdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SellerId")
    public JAXBElement<CustIdType> createSellerId(CustIdType value) {
        return new JAXBElement<CustIdType>(_SellerId_QNAME, CustIdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustIdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustId")
    public JAXBElement<CustIdType> createCustId(CustIdType value) {
        return new JAXBElement<CustIdType>(_CustId_QNAME, CustIdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "CustPermId")
    public JAXBElement<String> createCustPermId(String value) {
        return new JAXBElement<String>(_CustPermId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NetworkTrnInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "NetworkTrnInfo")
    public JAXBElement<NetworkTrnInfoType> createNetworkTrnInfo(NetworkTrnInfoType value) {
        return new JAXBElement<NetworkTrnInfoType>(_NetworkTrnInfo_QNAME, NetworkTrnInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BankInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BankInfo")
    public JAXBElement<BankInfoType> createBankInfo(BankInfoType value) {
        return new JAXBElement<BankInfoType>(_BankInfo_QNAME, BankInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BranchName")
    public JAXBElement<String> createBranchName(String value) {
        return new JAXBElement<String>(_BranchName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "BranchId")
    public JAXBElement<String> createBranchId(String value) {
        return new JAXBElement<String>(_BranchId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SvcRqType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "SvcRq")
    public JAXBElement<SvcRqType> createSvcRq(SvcRqType value) {
        return new JAXBElement<SvcRqType>(_SvcRq_QNAME, SvcRqType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "Cut")
    public JAXBElement<String> createCut(String value) {
        return new JAXBElement<String>(_Cut_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrgIdListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrgIdList")
    public JAXBElement<OrgIdListType> createOrgIdList(OrgIdListType value) {
        return new JAXBElement<OrgIdListType>(_OrgIdList_QNAME, OrgIdListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrgIdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrgId")
    public JAXBElement<OrgIdType> createOrgId(OrgIdType value) {
        return new JAXBElement<OrgIdType>(_OrgId_QNAME, OrgIdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrgClientType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "OrgClient")
    public JAXBElement<OrgClientType> createOrgClient(OrgClientType value) {
        return new JAXBElement<OrgClientType>(_OrgClient_QNAME, OrgClientType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GroupHeaderInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v1/", name = "GroupHeaderInfo")
    public JAXBElement<GroupHeaderInfoType> createGroupHeaderInfo(GroupHeaderInfoType value) {
        return new JAXBElement<GroupHeaderInfoType>(_GroupHeaderInfo_QNAME, GroupHeaderInfoType.class, null, value);
    }

}
