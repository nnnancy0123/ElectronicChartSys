/**
 * ENTERを押下時、カラムにひとつずつフォーカスする
 */
 $(() => {
    // :focusable はマイナスのtabindexを含む
    //  ⇒enter時に次項目へ移動するためのイベント対象のため含めている。
    const elements = ':focusable:not(a)';
    $(elements).keypress((e) => {
      if (e.key === 'Enter' || e.key === '\n') {
        if (e.ctrlKey && e.target.tagName === 'TEXTAREA' ) {
          // Ctrl＋Enterで改行処理を行う
          const t = e.target;           
          const {selectionStart: start, selectionEnd: end} = t;
          t.value = `${t.value.slice(0, start)}\n${t.value.slice(end)}`;
          t.selectionStart = t.selectionEnd = start + 1;             
          return;
        } else if (e.ctrlKey) {
          return;
        }
        // submitしない
        e.preventDefault();
        //focus可能な項目が入れ子になっている場合、targetのみで処理する
        e.stopPropagation();
        // tabindex順に移動するためソート
        let sortedList = $(elements).sort((a,b) => {
          if(a.tabIndex && b.tabIndex) {
            return a.tabIndex - b.tabIndex; 
          } else if(a.tabIndex && !b.tabIndex) {
            return -1;
          } else if(!a.tabIndex && b.tabIndex) {
            return 1;
          }
          return 0;
        });
        if (e.target.tabIndex < 0) {
          // tabindexがマイナスの場合、DOM上で次の項目へ移動するためソート前の項目から検索する
          sortedList = elements;
        }
        // 現在の項目位置から、移動先を取得する
        const index = $(sortedList).index(e.target);
        const nextFilter = e.shiftKey ? `:lt(${index}):last` : `:gt(${index}):first`;            
        const nextTarget = $(sortedList).filter(nextFilter);
        // shift + enterでtagindexがマイナスの項目へ移動するのを防ぐ
        if (!nextTarget.length || nextTarget[0].tabIndex < 0) return;
        // フォーカス移動＋文字列選択
        nextTarget.focus();
        if (typeof nextTarget.select === 'function' && nextTarget[0].tagName === 'INPUT') {
          nextTarget.select();
        }
      }
    });
  });