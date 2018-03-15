package cn.zhiyingyun.zone.entity;

public class SizePair {
  private int w;
  private int h;

  public SizePair(int w, int h) {
    this.setW(w);
    this.setH(h);
  }

  public int getH() {
    return h;
  }

  public void setH(int h) {
    this.h = h;
  }

  public int getW() {
    return w;
  }

  public void setW(int w) {
    this.w = w;
  }
}
