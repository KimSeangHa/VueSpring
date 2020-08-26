import axios from 'axios'

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
     * 메모 리스트
     *
     * @param {object} state
     * @param {array} payload
     */
     getMemoList (state) {
       axios.post('http://localhost:9090/webservice/getMemoList')
        .then((result) => {
            if (result.status === 200) {
              console.log(result.data)
              state.memoList = result.data
            } else {
              alert("등록을 실패하였습니다. 관리자에게 문의해주세요.")
            }
        })
        .catch(e => {
            alert("Error : 관리자에게 문의해주세요.")
            console.log(e)
        }) 
     },
    /**
     * 메모 등록
     *
     * @param {object} state
     * @param {array} payload
     */
     addMemo (state, payload) {
       console.log('## addMemo ##')
        const memoData = { content: payload.content, id: payload.id, regDate: payload.regDate }
        state.memoList.push(memoData)
        
        axios.post('http://localhost:9090/webservice/addMemo', { 
           content: payload.content,
           id: payload.id,
           regDate: payload.regDate,
           curDate: payload.curDate
         })
         .then((result) => {
            if (result.data === 0) {
              alert("등록을 완료하였습니다.")
            } else {
              alert("등록을 실패하였습니다. 관리자에게 문의해주세요.")
            }
         })
         .catch(e => {
             alert("Error : 관리자에게 문의해주세요.")
             console.log(e)
         }) 
      },
    /**
     * 메모 삭제
     *
     * @param {object} state
     * @param {array} payload
     */
     deleteMemo (state, payload) {
         console.log('## DeleteMemo ##')
         state.memoList.splice(payload.index, 1)
         
         axios.post('http://localhost:9090/webservice/deleteMemo', { 
            id: payload.item.id
         })
         .then((result) => {
             if (result.data === 0) {
               alert("삭제를 완료하였습니다")
             } else {
               alert("삭제를 실패하였습니다. 관리자에게 문의해주세요.")
             }
         })
         .catch(e => {
             alert("Error : 관리자에게 문의해주세요.")
             console.log(e)
         })
      },
     /**
      * 메모 수정
      *
      * @param {object} state
      * @param {Object} payload
      */
      rewriteMemo (state, payload) {
        console.log('## rewriteMemo ##')
        state.memoList[payload.index].content = payload.content
        state.memoList[payload.index].modifyDate = payload.modifyDate

        axios.post('http://localhost:9090/webservice/rewriteMemo', { 
            id: payload.item.id,
            content: payload.content,
            modifyDate: payload.modifyDate
         })
         .then((result) => {
             if (result.data === 0) {
               alert("수정을 완료하였습니다")
             } else {
               alert("수정을 실패하였습니다. 관리자에게 문의해주세요.")
             }
         })
         .catch(e => {
             alert("Error : 관리자에게 문의해주세요.")
             console.log(e)
         })
      }
    },
  
    /**
     * component의 method와 유사한 역할을 합니다.
     * 비동기 처리를 합니다.
     * this.$store.dispatch('sample/getTodos, [payload])
     */
    actions: {}
  }
  