import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DatatableItem } from '../../models/datatable.item';
import { IconProp } from '@fortawesome/fontawesome-svg-core';
import * as $ from 'jquery';
import { log } from 'util';
import { Uikit } from '../../include/uikit';
import { Subject } from 'rxjs';

@Component({
  selector: 'bdb-datatable',
  templateUrl: './bdb-datatable.component.html'
})
export class BdbDatatableComponent implements OnInit {

  @Input('customClass') class: string = '';
  @Input('itemIcon') icon: IconProp;
  @Input('headers') headers: DatatableItem[];
  @Input('data') items: {}[] = [];
  @Input('options') options: BdbDatatableOptions = new BdbDatatableOptions();
  @Input('useRowModal') usingRowModal: boolean = false;
  @Input('useClick') usingClick: boolean = false;
  @Input('itemsPerPage') itemsPerPage: Subject<number>;
  @Input('loadingData') loadingData: boolean = false;
  @Input('showIcon') showIcon: boolean = true;
  @Input('useModal') usingModal: boolean = false;

  @Output('selectedItem') selectedItem: EventEmitter<{ index: number, item: {} }> = new EventEmitter<{ index: number, item: {} }>();

  public sort: string = '0';
  public asc: boolean = false;
  public config: any;
  public doingAction: boolean = false;

  constructor() {
  }

  ngOnInit() {

    // Pagination config
    this.config = {
      itemsPerPage: 10,
      currentPage: 1,
      totalItems: this.items.length
    };

    // When items per page change
    this.itemsPerPage.subscribe(event => {
      this.config = {
        itemsPerPage: event,
        currentPage: 1,
        totalItems: this.items.length
      };
    });

    // Doing action
    this.doingAction = this.usingRowModal
      || this.usingClick;

  }

  /**
   * Set selected item and send to parent component ========================================================================================
   * @param item
   */
  public setSelectedItem(item: { index: number, item: {} }): void {
    this.selectedItem.emit(item);
  }

  /**
   * Order by expression ===================================================================================================================
   * @param key Key
   */
  public orderExpression(key: number): void {
    this.asc = `${key}` !== this.sort ? false : !this.asc;
    this.sort = `${key}`;
  }

  /**
   * Active title table ====================================================================================================================
   * @param key Key
   * @param index
   */
  public active(key: string, index: number): boolean {
    return key === `${index}`;
  }

  /**
   * Open modal if is enabled ==============================================================================================================
   * @param data modal data.
   */
  public openModal(data: { index: number, item: {} }): void {
    if (this.usingModal) {
      Uikit.modal('#datatable-modal').show();
    }
  }

  /**
   * Validate status from data =============================================================================================================
   * @param value status value
   * @param ret status translation
   */
  public validateStatus(value: any, ret: string = 'color'): string {
    let color = '#fff';
    let name = 'Estado';

    this.options.status_validation.forEach(item => {
      if (item.status === value) {
        color = item.color;
        name = item.name;
      }
    });

    switch (ret) {
      default:
      case 'color':
        return color;
      case 'name':
        return name;
    }
  }

  /**
   * Change the page for table pagination ==================================================================================================
   * @param val event value
   */
  public changeThePage(val: any): void {
    this.config.currentPage = val;
  }

  /**
   * When do action validation
   * @param index
   * @param item
   */
  public doAction(index: number, item: {}): void {
    item = Object.values(item);
    this.setSelectedItem({ index, item });

    // If Modal
    if (this.usingRowModal) {
      this.openModal({ index, item });
    }
  }

  /**
   * Strip accents =========================================================================================================================
   * @param value text to strip
   */
  public stripKey(value: string): string {
    // to lowercase
    value = value.toLowerCase();

    // Translator
    const translate_re = /[àáâãäçèéêëìíîïñòóôõöùúûüýÿÀÁÂÃÄÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝ]/g;
    const translate = 'aaaaaceeeeiiiinooooouuuuyyAAAAACEEEEIIIINOOOOOUUUUY';

    // Strip spaces and characters
    value = value
      .replace(/[-_\\\/.]/g, '');

    return (value.replace(translate_re, match => translate.substr(translate_re.source.indexOf(match) - 1, 1)));
  }
}

/**
 * Date format =============================================================================================================================
 */
export type DateFormatProp = 'dd/MM/yyyy HH:mm' | 'yyyy-MM-dd HH:mm' | 'dd/MM/yyyy' | 'yyyy-MM-dd';

/**
 * Datatable Options object ================================================================================================================
 */
export class BdbDatatableOptions {
  status_validation?: {
    status: string,
    name: string,
    color: string
  }[];
  date_format?: DateFormatProp = 'dd/MM/yyyy HH:mm';
}
