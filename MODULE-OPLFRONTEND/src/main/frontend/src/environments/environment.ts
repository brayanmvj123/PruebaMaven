export const host = 'http://localhost:8081/?api=';

export const environment = {
  production: false,
  base: '/', // Context base
  domain: 'localhost', // Cookie domain
  time_to_refresh: 30, // Time in seconds
  notification_duration: 10, // Notification duration in seconds
  expiration_token: 10, // Time in minutes
  mobile_size: 850, // Mobile size
  notification: {
    duration: 10000, // Time in millis
    position: 'bottom-right' // (top-left, top-center, top-right, bottom-left, bottom-right, bottom-center)
  },
  api: { // Rest-full api data
    auth_url: `${host}/OPLAUTH/auth/sso`, //&status=401
    auth_refresh_url: `${host}/OPLAUTH/auth/sso/refresh`,
    create_ftp_user: `${host}/ftp/user/registration`,
    get_ftp_users: `${host}/OPLSERVFRONT/ftp/user/all`,
    create_ftp_connection: `${host}/ftp/connection/creation`,
    user_roles: `${host}/OPLAUTH/auth/user/roles`,
    search_nuser: `${host}/users/search`,
    change_user_state: `${host}/users/availability`,
    get_users: `${host}/OPLAUTH/users/all`,
    get_domains: `${host}/OPLAUTH/auth/domain/list`,
    get_auth_type: `${host}/OPLAUTH/auth/type`,
    download_user_excel: `${host}/file/excel/generation`,
    get_base_type: `${host}/OPLSERVFRONT/ParametricTable/Operation/read/baseType`,
    get_period_type: `${host}/OPLSERVFRONT/ParametricTable/Operation/read/periodType`,
    get_rate_type: `${host}/OPLSERVFRONT/ParametricTable/Operation/read/rateType`,
    get_term_type: `${host}/OPLSERVFRONT/ParametricTable/Operation/read/termType`,
    get_calendar_notenabled: `${host}/OPLSERVFRONT/ParametricTable/Operation/read/yearCalendar/noHabil`,
    calculate_days_cdt: `${host}/OPLSIMULADOR/CDTSDesmaterializado/v2/simulador/calculoDiasCDT`,
    simulation_fees_cdt: `${host}/OPLSIMULADOR/CDTSDesmaterializado/v2/simulacionCuota`,
    get_pg_semanal: `${host}/OPLSERVFRONT/Information/Office/expirationDate/weekly`,
    get_pg: `${host}/OPLSERVFRONT/Information/Office/expirationDate/daily`,
    get_start_date_period: `${host}/OPLSIMULADOR/CDTSDesmaterializado/v2/calculoFechaInicioPeriodo`,
    get_rates_variables: `${host}/OPLSIMULADOR/CDTSDesmaterializado/v2/tasaVariable/ttf/obtenertasa`,
    closeConciliation: `${host}/OPLSERVFRONT/Information/Office/expirationDate/daily/confirmation`,
    closeConciliationWeekly: `${host}/OPLSERVFRONT/Information/Office/expirationDate/weekly/confirmation`,
    update_pg: `${host}/OPLSERVFRONT/Information/Office/expirationDate/daily/update`,
    update_pgWeekly: `${host}/OPLSERVFRONT/Information/Office/expirationDate/weekly/update`,
    validate_offices: `${host}/OPLSERVFRONT/Information/office/weekly/findOfficeWithoutEmail`,    
    send_email: `${host}/OPLSERVFRONT/Information/Office/expirationDate/weekly/sendFilesToEmail`,    
    get_active_user_list_by_office: `${ host }/OPLSERVFRONT/Information/UserByOffice`,
    post_user_by_office_reg: `${ host }/OPLSERVFRONT/Information/UserByOffice`,
    delete_user_office_list: `${ host }/OPLSERVFRONT/Information/UserByOffice/delete/:id`,    
    getUserItemOfficeList: `${ host }/OPLSERVFRONT/Information/UserByOffice/:id`,
    editUserListOffice: `${ host }/OPLSERVFRONT/Information/UserByOffice/update/:id`,
    get_office_list: `${ host }/OPLSERVFRONT/Information/office`

  },
  components: {
    DASHBOARD_HOME: false,
    ADMIN_USERS: false,
    ADMIN_CONNECTIONS: false,
    SIMULATOR: false,
    DAILY_SETTLEMENT: false,
    WEEKLY_SETTLEMENT: false,
    OFFICELIST: true,
    OFFICELIST_REG: false,
    OFFICELIST_ITEM: false,
    USERLIST_OFFICE: false
  },
  simulador:{
    calendario_no_habiles:{
      semana:[0,6]
    }
  }

};
