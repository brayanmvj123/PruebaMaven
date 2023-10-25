export const environment = {
  production: true,
  base: '/opalo/',
  domain: 'opalodes.banbta.net',
  time_to_refresh: 10,
  notification_duration: 10,
  expiration_token: 10, // Time in minutes
  mobile_size: 850, // Mobile size
  notification: {
    duration: 10000, // Time in millis
    position: 'bottom-right' // (top-left, top-center, top-right, bottom-left, bottom-right, bottom-center)
  },
  api: {
    auth_url: '/OPLAUTH/auth/sso',
    auth_refresh_url: '/OPLAUTH/auth/sso/refresh',
    create_ftp_user: '/OPLSERVFRONT/ftp/user/registration',
    get_ftp_users: '/OPLSERVFRONT/ftp/user/all',
    create_ftp_connection: '/OPLSERVFRONT/ftp/connection/creation',
    user_roles: '/OPLAUTH/auth/user/roles',
    search_nuser: '/OPLAUTH/users/search',
    change_user_state: '/OPLAUTH/users/availability',
    get_users: '/OPLAUTH/users/all',
    get_domains: '/OPLAUTH/auth/domain/list',
    get_auth_type: '/OPLAUTH/auth/type',
    download_user_excel: '/OPLSERVFRONT/file/excel/generation',
    get_base_type: '/OPLSERVFRONT/ParametricTable/Operation/read/baseType',
    get_period_type: '/OPLSERVFRONT/ParametricTable/Operation/read/periodType',
    get_rate_type: '/OPLSERVFRONT/ParametricTable/Operation/read/rateType',
    get_term_type: '/OPLSERVFRONT/ParametricTable/Operation/read/termType',
    get_calendar_notenabled: '/OPLSERVFRONT/ParametricTable/Operation/read/yearCalendar/noHabil',
    calculate_days_cdt: '/OPLSIMULADOR/CDTSDesmaterializado/v2/simulador/calculoDiasCDT',
    simulation_fees_cdt: '/OPLSIMULADOR/CDTSDesmaterializado/v2/simulacionCuota',
    get_pg_semanal: '/OPLSERVFRONT/Information/Office/expirationDate/weekly',
    get_pg: '/OPLSERVFRONT/Information/Office/expirationDate/daily',
    get_start_date_period: '/OPLSIMULADOR/CDTSDesmaterializado/v2/calculoFechaInicioPeriodo',
    get_rates_variables: '/OPLSIMULADOR/CDTSDesmaterializado/v2/tasaVariable/ttf/obtenertasa',
    closeConciliation: '/OPLSERVFRONT/Information/Office/expirationDate/daily/confirmation',
    closeConciliationWeekly: '/OPLSERVFRONT/Information/Office/expirationDate/weekly/confirmation',
    update_pg: '/OPLSERVFRONT/Information/Office/expirationDate/daily/update',
    update_pgWeekly: '/OPLSERVFRONT/Information/Office/expirationDate/weekly/update',
    validate_offices: '/OPLSERVFRONT/Information/office/weekly/findOfficeWithoutEmail',    
    send_email: '/OPLSERVFRONT/Information/Office/expirationDate/weekly/sendFilesToEmail',    
    get_active_user_list_by_office: '/OPLSERVFRONT/Information/UserByOffice',
    post_user_by_office_reg: '/OPLSERVFRONT/Information/UserByOffice',
    delete_user_office_list: '/OPLSERVFRONT/Information/UserByOffice/delete/:id',
    getUserItemOfficeList: '/OPLSERVFRONT/Information/UserByOffice/:id',
    editUserListOffice: '/OPLSERVFRONT/Information/UserByOffice/update/:id',    
    get_office_list: '/OPLSERVFRONT/Information/office'
  },
  domains: [ // BDB Domains
    { id: 'DES1', name: 'BANCO DE BOGOTÁ' }
  ],
  components: {
    DASHBOARD_HOME: false,
    ADMIN_USERS: false,
    ADMIN_CONNECTIONS: false,
    ADMIN_RES: false,
    SIMULATOR: false,
    DAILY_SETTLEMENT: false,
    WEEKLY_SETTLEMENT: false,
    OFFICELIST: false,
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
