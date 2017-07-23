 public static final 的变量同时出现在 superclass与interface中（假设该变量为  int a＝0；）
当子类同时继承superclass而且实现interface时，子类在使用a时（如：return a；）
compiler会出现错误信息：提示，使用该变量a时模糊不清晰ambiguous。
解决方案：使用interface.a  以及 super.a 分别access希望使用的a