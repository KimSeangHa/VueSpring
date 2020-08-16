export default {
    /**
     * 하기 항목들에 namespace를 사용합니다.
     * [getters, actions, mutaions]
     */
    namespaced: true,
  
    /**
     * component의 data와 유사한 역할을 합니다.
     * 컴포넌트 내에서 참조방법
     * 1. this.$store.state.sample.todos
     * 2. computed: { ...mapState('sample', ['todos']) }
     */
    state: {
      memoList: []
    },
  
    /**
     * component의 computed와 유사한 역할을 합니다.
     * 컴포넌트 내에서 참조방법
     * computed: { ...mapGetters('sample', ['todosCount']) }
     */
    getters: {
      todosCount: state => {
        return state.todos.length
      }
    },
  
    /**
     * component의 methods와 유사한 역할을 합니다.
     * state를 변경할 때 사용합니다.
     * this.$store.commit('sample/setTodos', [payload])
     */
    mutations: {
    /**
     * 메모 등록
     *
     * @param {object} state
     * @param {array} payload
     */
     addMemo (state, payload) {
        const memoData = { title: payload.title, content: payload.content, id: payload.id }
        state.memoList.push(memoData)
        localStorage.setItem('memoList', JSON.stringify(state.memoList))
      },
    /**
     * 메모 삭제
     *
     * @param {object} state
     * @param {array} payload
     */
     deleteMemo (state, payload) {
         console.log(payload.item)
         console.log(payload.index)
         state.memoList.splice(payload.index, 1)
         localStorage.setItem('memoList', JSON.stringify(state.memoList))
        // localStorage.removeItem('memoList')
      },
     /**
      * 메모 수정
      *
      * @param {object} state
      * @param {Object} payload
      */
      rewriteMemo (state, payload) {
        if ( payload.type == 1 ) {
          console.log('aa')
          state.memoList[payload.index].title = payload.retitle
          console.log(state.memoList[payload.index])
          localStorage.setItem('memoList', JSON.stringify(state.memoList))
        } else if ( payload.type == 2 ) {
          console.log('bb')
          state.memoList[payload.index].content = payload.recontent
          console.log(state.memoList[payload.index])
          localStorage.setItem('memoList', JSON.stringify(state.memoList))
        } else {
          console.log('cc')
          const params = payload.recontent.split('/')
          console.log(params[0])
          state.memoList[payload.index].title = params[0]
          state.memoList[payload.index].content = params[1]
          console.log(state.memoList[payload.index])
          localStorage.setItem('memoList', JSON.stringify(state.memoList))
        }

        /*
        console.log("######")
        console.log(payload.item)
        state.memoList[payload.index].content = payload.item
        console.log(state.memoList[payload.index])
        localStorage.setItem('memoList', JSON.stringify(state.memoList))
        */
      // localStorage.removeItem('memoList')
      },
      /**
       * todo 목록 갱신
       *
       * @param {object} state
       * @param {arrary} payload todo 목록
       */
      setTodos (state, payload) {
        state.todos = payload
      },
      /**
       * todo 아이템 추가
       *
       * @param {object} state
       * @param {string} todoItem
       */
      addOneItem (state, todoItem) {
        const obj = { completed: false, item: todoItem }
        localStorage.setItem(todoItem, JSON.stringify(obj))
        state.todoItems.push(obj)
      },
      /**
       * todo 아이템 삭제
       *
       * @param {object} state
       * @param {object} payload
       */
      removeItem (state, payload) {
        localStorage.removeItem(payload.todoItem.item)
        state.todoItems.splice(payload.index, 1)
      },
      /**
       * 아이템 상태 변경 (check/uncheck 처리)
       *
       * @param {object} state
       * @param {object} payload
       */
      toggleItem (state, payload) {
        state.todoItems[payload.index].completed = !state.todoItems[payload.index]
          .completed
        localStorage.removeItem(payload.todoItem.item)
        localStorage.setItem(
          payload.todoItem.item,
          JSON.stringify(payload.todoItem)
        )
      },
      /**
       * todo 목록 비우기
       *
       * @param {object} state
       */
      clearAllItems (state) {
        localStorage.clear()
        state.todoItems = []
      },
      /**
       * todo item 설정
       *
       * @param {object} state
       * @param {array} payload
       */
      setTodoItems (state, payload) {
        state.todoItems = payload
      }
    },
  
    /**
     * component의 method와 유사한 역할을 합니다.
     * 비동기 처리를 합니다.
     * this.$store.dispatch('sample/getTodos, [payload])
     */
    actions: {}
  }
  