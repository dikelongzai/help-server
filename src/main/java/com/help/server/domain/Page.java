package com.help.server.domain;

/**
 * Created by dikelongzai 15399073387@163.com on 2017-01-14
 * .
 */
public class Page {
    private int rowTotal;// �ܼ�¼��
    private int pageSize = 10;// ÿҳ��¼��

    private int count;// ��ǰҳ��

    private int total;// ��ҳ��
    private int beginIndex;//��ʼ��¼�±�
    private int endIndex;//��ֹ��¼�±�

    /**
     * ʹ���ܼ�¼������ǰҳ�빹��
     *
     * @param rowTotal
     * @param count
     *            ҳ�룬��1��ʼ
     */
    public Page(int totalRow, int count) {
        this.rowTotal = totalRow;
        this.count = count;
        calculate();
    }

    /**
     * ʹ���ܼ�¼������ǰҳ���ÿҳ��¼������
     *
     * @param rowTotal
     * @param count
     *            ҳ�룬��1��ʼ
     * @param pageSize
     *            Ĭ��30��
     */
    public Page(int totalRow, int count, int pageSize) {
        this.rowTotal = totalRow;
        this.count = count;
        this.pageSize = pageSize;
        calculate();
    }

    private void calculate() {
        total = rowTotal / pageSize + ((rowTotal % pageSize) > 0 ? 1 : 0);
        if (count > total) {
            count = total;
        } else if (count < 1) {
            count = 1;
        }
        beginIndex = (count - 1) * pageSize ;
        endIndex = beginIndex + pageSize ;
        if (endIndex > rowTotal) {
            endIndex = rowTotal;
        }
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalRow() {
        return rowTotal;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
